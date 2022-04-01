package com.digitalstork.devbookskata.controller;

import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.dto.GetAllBooksResponse;
import com.digitalstork.devbookskata.dto.PurchaseBooksResponse;
import com.digitalstork.devbookskata.exception.ErrorResponse;
import com.digitalstork.devbookskata.service.DevelopmentBookService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/api/books")
@Tag(name = "Development Books Controller", description = "Contains Endpoints to view available books and purchase them")
public class DevelopmentBookController {

    @Autowired
    private DevelopmentBookService developmentBookService;

    @Operation(summary = "API for read all available development Books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns a list of Books",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = GetAllBooksResponse.class))}),
            @ApiResponse(responseCode = "400", description = "There is no Available Books",
                    content = @Content)
    })
    @GetMapping("/getAll")
    ResponseEntity<GetAllBooksResponse> getDevelopmentBooks() {
        return ResponseEntity.ok(new GetAllBooksResponse(developmentBookService.getAllDevelopmentBooks()));
    }

    @Operation(summary = "API to calculate price of a list of books")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Returns the price with Discount",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PurchaseBooksResponse.class))}),
            @ApiResponse(responseCode = "400", description = "Book requested does not exist or It is out of stock",
                    content = {@Content(mediaType = "application/json",schema = @Schema(implementation = ErrorResponse.class))}),
            @ApiResponse(responseCode = "500", description = "Invalid request parameters",
                    content = @Content)
    })
    @PostMapping("/purchase")
    ResponseEntity<PurchaseBooksResponse> purchaseBooks(@RequestBody List<DevelopmentBookPurchaseDto> bookDtos) {
        return ResponseEntity.ok(new PurchaseBooksResponse(developmentBookService.purchaseBooks(bookDtos)));
    }
}
