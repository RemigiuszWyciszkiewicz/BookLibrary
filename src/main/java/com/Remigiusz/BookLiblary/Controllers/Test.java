package com.Remigiusz.BookLiblary.Controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Test {

    @GetMapping("/test")
    ResponseEntity<?> test() {


        return null;
    }

}
