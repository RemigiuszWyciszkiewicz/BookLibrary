package com.Remigiusz.BookLiblary.Service;

import com.Remigiusz.BookLiblary.DataModels.Book;
import com.Remigiusz.BookLiblary.Response.BookResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

public class BooksFromGoogleApiProvider {

    static final String ALL_BOOKS_FROM_GOOGLE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40&fields=items";



    public List<BookResponse> fetchResponseBookList() throws IOException {


        List<BookResponse> responeBooks = deserializeDataFromJsonToBook().stream().map(book -> {
            String thumbnailUrl;
            if (book.getVolumeInfo().getImageLinks() == null) {
                thumbnailUrl = null;
            } else thumbnailUrl = book.getVolumeInfo().getImageLinks().get("thumbnail");

            return new BookResponse(
                    book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier(),
                    book.getVolumeInfo().getTitle(),
                    book.getVolumeInfo().getSubtitle(),
                    book.getVolumeInfo().getPublisher(),
                    book.getVolumeInfo().getPublishedDate(),
                    book.getVolumeInfo().getDescription(),
                    book.getVolumeInfo().getPageCount(),
                    thumbnailUrl,
                    book.getVolumeInfo().getLanguage(),
                    book.getVolumeInfo().getPreviewLink(),
                    book.getVolumeInfo().getAverageRating(),
                    book.getVolumeInfo().getAuthors(),
                    book.getVolumeInfo().getCategories());
        }).collect(Collectors.toList());

        return responeBooks;
    }

    private List<Book> deserializeDataFromJsonToBook() {
        RestTemplate restTemplate = new RestTemplate();
        String s = restTemplate.getForObject(ALL_BOOKS_FROM_GOOGLE_API_URL, String.class);

        JSONObject objects = new JSONObject(s);
        JSONArray jsonArray = objects.getJSONArray("items");

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);


        TypeReference ref = new TypeReference<List<Book>>() {};
        List<Book> list = null;
        try {
            list = objectMapper.readValue(jsonArray.toString(), ref);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return list;
    }

}
