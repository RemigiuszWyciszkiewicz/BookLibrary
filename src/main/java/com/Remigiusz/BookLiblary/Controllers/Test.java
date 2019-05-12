package com.Remigiusz.BookLiblary.Controllers;

import com.Remigiusz.BookLiblary.Response.ResponeBook;
import com.Remigiusz.BookLiblary.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class Test {

    @Autowired
    BookService bookService;

    @GetMapping("/test")
    ResponseEntity<List<ResponeBook>> test() throws IOException {


        return ResponseEntity.ok(bookService.fetchResponseBookList());
    }

}
