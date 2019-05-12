package com.Remigiusz.BookLiblary;

import com.Remigiusz.BookLiblary.DataModels.Book;
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
import java.util.ArrayList;
import java.util.List;
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
		/*objectMapper.configure(DeserializationFeature.ACCEPT_FLOAT_AS_INT,true);*/


		Book[] list = objectMapper.readValue(jsonArray.toString(),Book[].class);












	}

}

