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

import javax.annotation.PostConstruct;
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

    private List<ResponeBook> listOfBooksFromGoogleApi;

    @PostConstruct
    private void initListOfBooksFromGoogleApi() throws IOException {

        BooksFromGoogleApiProvider booksFromGoogleApiProvider = new BooksFromGoogleApiProvider();

        this.listOfBooksFromGoogleApi = booksFromGoogleApiProvider.fetchResponseBookList();

    }

    public Optional<ResponeBook> getBookByISBN(String isbn) {

       Optional<ResponeBook> optionalResponeBook =
               listOfBooksFromGoogleApi.stream().filter(responeBook -> responeBook.getIsbn().equals(isbn)).findAny();

        return optionalResponeBook;
    }

    public List<ResponeBook> getBooksByCategory(String category) {

        List<ResponeBook> booksWithSpecificCategory=this.listOfBooksFromGoogleApi.stream()
                .filter(responeBook -> responeBook.getCategories() != null)
                .filter(responeBook -> responeBook.getCategories().contains(category)).collect(Collectors.toList());

        return booksWithSpecificCategory;
    }





}
