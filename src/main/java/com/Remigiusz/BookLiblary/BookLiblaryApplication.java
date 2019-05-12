package com.Remigiusz.BookLiblary;

import com.Remigiusz.BookLiblary.DataModels.Book;
import com.Remigiusz.BookLiblary.Response.ResponeBook;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.internal.filter.ValueNode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class BookLiblaryApplication {


	public static void main(String[] args) throws IOException {
		SpringApplication.run(BookLiblaryApplication.class, args);


		RestTemplate restTemplate = new RestTemplate();
		String s = restTemplate.getForObject("https://www.googleapis.com/books/v1/volumes?q=java&maxResults=15&fields=items", String.class);


		JSONObject objects = new JSONObject(s);
		JSONArray jsonArray=objects.getJSONArray("items");


		ObjectMapper objectMapper = new ObjectMapper();
		objectMapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);



		TypeReference ref = new TypeReference<List<Book>>(){};
		List<Book> list = objectMapper.readValue(jsonArray.toString(),ref);






		List<ResponeBook> responeBooks = list.stream().map(book -> {

			String link;
			if(book.getVolumeInfo().getImageLinks() == null) {
				link = null;
			}else link=book.getVolumeInfo().getImageLinks().get("thumbnail");

			return new ResponeBook(
					book.getVolumeInfo().getIndustryIdentifiers().get(0).getIdentifier(),
					book.getVolumeInfo().getTitle(),
					book.getVolumeInfo().getSubtitle(),
					book.getVolumeInfo().getPublisher(),
				new Timestamp(book.getVolumeInfo().getPublishedDate().getTime()).getNanos(),
					book.getVolumeInfo().getDescription(),
					book.getVolumeInfo().getPageCount(),
					link,
					book.getVolumeInfo().getLanguage(),
					book.getVolumeInfo().getPreviewLink(),
					book.getVolumeInfo().getAverageRating(),
					book.getVolumeInfo().getAuthors(),
					book.getVolumeInfo().getCategories());
		} ).collect(Collectors.toList());













	}

}

