package com.digitalstork.devbookskata.controller;

import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.dto.GetAllBooksResponse;
import com.digitalstork.devbookskata.dto.PurchaseBooksResponse;
import com.digitalstork.devbookskata.service.DevelopmentBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
public class DevelopmentBookController {

    @Autowired
    private DevelopmentBookService developmentBookService;

    @GetMapping("/getAll")
    ResponseEntity<GetAllBooksResponse> getDevelopmentBooks() {
        return ResponseEntity.ok(new GetAllBooksResponse(developmentBookService.getAllDevelopmentBooks()));
    }

    @PostMapping("/purchase")
    ResponseEntity<PurchaseBooksResponse> purchaseBooks(@RequestBody List<DevelopmentBookPurchaseDto> bookDtos) {
        return null;
    }
}
