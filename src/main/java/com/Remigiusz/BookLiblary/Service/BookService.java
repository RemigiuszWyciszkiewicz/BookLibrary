package com.Remigiusz.BookLiblary.Service;

import com.Remigiusz.BookLiblary.DataModels.Book;
import com.Remigiusz.BookLiblary.Response.ResponeBook;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class BookService {

    static final String ALL_BOOKS_FROM_GOOGLE_API_URL = "https://www.googleapis.com/books/v1/volumes?q=java&maxResults=40&fields=items";

    public Optional<ResponeBook> getBookByISBN(String isbn) throws IOException {

       Optional<ResponeBook> optionalResponeBook =
        fetchResponseBookList().stream().filter(responeBook -> responeBook.getIsbn().equals(isbn)).findAny();

        return optionalResponeBook;
    }

    public List<ResponeBook> getResponseBooksByCategory(String category) throws IOException {

        System.out.println(fetchResponseBookList().stream().filter(responeBook -> responeBook.getCategories().contains(category)).count());
        /*List<ResponeBook> responeBookListBySpecificCategory = */
        return null;
    }


    private List<ResponeBook> fetchResponseBookList() throws IOException {

        List<ResponeBook> responeBooks = deserializeDataFromJsonToBook().stream().map(book -> {
            String thumbnailUrl;
            if (book.getVolumeInfo().getImageLinks() == null) {
                thumbnailUrl = null;
            } else thumbnailUrl = book.getVolumeInfo().getImageLinks().get("thumbnail");

            return new ResponeBook(
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
