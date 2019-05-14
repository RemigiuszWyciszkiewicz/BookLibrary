package com.Remigiusz.BookLiblary.Service;


import com.Remigiusz.BookLiblary.Response.AuthorRatingResponse;
import com.Remigiusz.BookLiblary.Response.BookResponse;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;


@Service
public class BookService {

    private List<BookResponse> listOfBooksFromGoogleApi;

    @PostConstruct
    private void initListOfBooksFromGoogleApi() throws IOException {

        BooksFromGoogleApiProvider booksFromGoogleApiProvider = new BooksFromGoogleApiProvider();
        this.listOfBooksFromGoogleApi = booksFromGoogleApiProvider.fetchResponseBookList();

    }

    public Optional<BookResponse> getBookByISBN(String isbn) {

       Optional<BookResponse> optionalResponeBook =
               listOfBooksFromGoogleApi.stream().filter(responeBook -> responeBook.getIsbn().equals(isbn)).findAny();

        return optionalResponeBook;
    }

    public List<BookResponse> getBooksByCategory(String category) {

        getAuthorsRating();

        List<BookResponse> booksWithSpecificCategory=this.listOfBooksFromGoogleApi.stream()
                .filter(responeBook -> responeBook.getCategories() != null)
                .filter(responeBook -> responeBook.getCategories().contains(category)).collect(Collectors.toList());

        return booksWithSpecificCategory;
    }

    public List<AuthorRatingResponse> getAuthorsRating()
    {

        // 1 - Convert list of all book into list of books without nulls as Author and Rating fields.
        List<BookResponse> bookWithoutNulls=listOfBooksFromGoogleApi.stream()
                .filter(responseBook -> responseBook.getAuthors() != null)
                .filter(responseBook -> responseBook.getAverageRating() != null)
                .collect(Collectors.toList());

        // 2 - Receiving list of authors
        List<String> authors=bookWithoutNulls.stream()
                .flatMap(responeBook -> responeBook.getAuthors().stream())
                .distinct()
                .collect(Collectors.toList());

        // 3 - Declaring list of object that consists author and rating fields
        List<AuthorRatingResponse> authorRatingResponsesList = new ArrayList<>();

        // 4 - Joining authors and thier books in nested loop
        IntStream.range(0, authors.size())
                .forEach(index_1 -> { IntStream.range(0, bookWithoutNulls.size()).forEach( index_2 -> {

                    if(bookWithoutNulls.get(index_2).getAuthors().contains(authors.get(index_1)))
                    authorRatingResponsesList.add(new AuthorRatingResponse(authors.get(index_1),bookWithoutNulls.get(index_2).getAverageRating()));

                } );
        });

        // 5 - Grouping by author
        Map<String, Double> averages = authorRatingResponsesList.stream().collect(Collectors.groupingBy(e -> e.getAuthor(),
               Collectors.averagingDouble(value -> value.getRating())));


        // 6 - Sort descending and map Map into BookResponse list
        List<AuthorRatingResponse> authorRatingResponses = averages.entrySet().stream()
                .sorted(Map.Entry.<String, Double>comparingByValue().reversed()).map(stringDoubleEntry -> {
                 return new AuthorRatingResponse(stringDoubleEntry.getKey(),stringDoubleEntry.getValue());
                } ).collect(Collectors.toList());


        return authorRatingResponses;
    }
}

