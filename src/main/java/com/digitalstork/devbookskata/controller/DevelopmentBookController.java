package com.digitalstork.devbookskata.controller;

import com.digitalstork.devbookskata.dto.GetAllBooksResponse;
import com.digitalstork.devbookskata.service.DevelopmentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/books")
public class DevelopmentBookController {

    @Autowired
    private DevelopmentBookService developmentBookService;

    @GetMapping("/getAll")
    ResponseEntity<GetAllBooksResponse> getDevelopmentBooks() {
        return ResponseEntity.ok(new GetAllBooksResponse(developmentBookService.getAllDevelopmentBooks()));
    }
}
