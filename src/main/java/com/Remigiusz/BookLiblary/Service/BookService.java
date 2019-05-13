package com.Remigiusz.BookLiblary.Service;


import com.Remigiusz.BookLiblary.Response.AuthorRatingResponse;
import com.Remigiusz.BookLiblary.Response.ResponeBook;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
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

        getAuthorsRating();

        List<ResponeBook> booksWithSpecificCategory=this.listOfBooksFromGoogleApi.stream()
                .filter(responeBook -> responeBook.getCategories() != null)
                .filter(responeBook -> responeBook.getCategories().contains(category)).collect(Collectors.toList());

        return booksWithSpecificCategory;
    }

    public List getAuthorsRating()
    {

        List<ResponeBook> bookWithoutNullsAsAuthorAndRating=listOfBooksFromGoogleApi.stream()
                .filter(responeBook -> responeBook.getAuthors() != null)
                .filter(responeBook -> responeBook.getAverageRating() != null)
                .collect(Collectors.toList());

        List<String> authors=bookWithoutNullsAsAuthorAndRating.stream()
                .flatMap(responeBook -> responeBook.getAuthors().stream())
                .distinct()
                .collect(Collectors.toList());

        List<AuthorRatingResponse> stringResponeBookMap = new ArrayList<>();

        for (int i = 0; i < authors.size(); i++) {
            for (int j = 0; j < bookWithoutNullsAsAuthorAndRating.size(); j++) {
                if(bookWithoutNullsAsAuthorAndRating.get(j).getAuthors().contains(authors.get(i))) {
                    stringResponeBookMap.add(new AuthorRatingResponse(authors.get(i),bookWithoutNullsAsAuthorAndRating.get(j).getAverageRating()));
                }
            }
        }

  /*      Map<String, Double> averages = stringResponeBookMap.stream().collect(
                        Collectors.groupingBy(e -> e.getAuthor(),
                                Collectors.averagingDouble(AuthorRatingResponse::getRating)));

*/



        Map<String,Double> stringDoubleMap=stringResponeBookMap.stream().collect(Collectors.groupingBy(w -> w.));







        return null;
    }





}
