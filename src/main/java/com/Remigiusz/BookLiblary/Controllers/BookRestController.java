package com.Remigiusz.BookLiblary.Controllers;

import com.Remigiusz.BookLiblary.Exceptions.NotFoundException;
import com.Remigiusz.BookLiblary.Response.ResponeBook;
import com.Remigiusz.BookLiblary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class BookRestController {

    @Autowired
    BookService bookService;

    @GetMapping("/book/{isbn}")
    ResponseEntity<ResponeBook> getSingleBook(@PathVariable(name = "isbn",required = true) String isbn) throws IOException {

       ResponeBook responeBook= bookService.getBookByISBN(isbn)
               .orElseThrow(() -> { throw new NotFoundException("There is not product with isbn - " + isbn); } );

        return ResponseEntity.ok(responeBook);
    }

    @GetMapping("/books/{category}")
    ResponseEntity<List<ResponeBook>> getBooksByCategory(@PathVariable(name = "category",required = true) String category) throws IOException {

        List<ResponeBook> responeBookList = bookService.getBooksByCategory(category);

        if(responeBookList.isEmpty()) throw new NotFoundException("There are not books with this category");
        else return ResponseEntity.ok(responeBookList);


    }

}
