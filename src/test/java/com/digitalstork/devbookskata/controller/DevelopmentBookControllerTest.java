package com.digitalstork.devbookskata.controller;

import com.digitalstork.devbookskata.DevbookskataApplication;
import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.dto.GetAllBooksResponse;
import com.digitalstork.devbookskata.dto.PurchaseBooksResponse;
import com.digitalstork.devbookskata.service.DevelopmentBookService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.ServerProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest(classes = DevbookskataApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class DevelopmentBookControllerTest {

    private final Integer singleBookPrice = 50;

    @MockBean
    DevelopmentBookService developmentBookService;

    @Autowired
    TestRestTemplate restTemplate;

    @Autowired
    private ServerProperties serverProperties;

    @LocalServerPort
    int port;


    @Test
    void shouldGetAllDevelopmentBooksWithSuccess() {
        //Given
        String apiUrl = "http://localhost:" + port +"/api/books/getAll";
        List<DevelopmentBookListDto> bookDtos = new ArrayList<>();
        bookDtos.add(new DevelopmentBookListDto(1L, "Clean Code", "Robert Martin, 2008", 5));
        bookDtos.add(new DevelopmentBookListDto(2L, "The Clean Coder", "Robert Martin, 2011", 5));
        bookDtos.add(new DevelopmentBookListDto(3L,"Clean Architecture", "Robert Martin, 2017", 5));
        bookDtos.add(new DevelopmentBookListDto(4L, "Test Driven Development by Example", "Kent Beck, 2003", 5));
        bookDtos.add(new DevelopmentBookListDto(5L, "Working Effectively With Legacy Code", "Michael C. Feathers, 2004", 5));

        //When
        Mockito.when(developmentBookService.getAllDevelopmentBooks()).thenReturn(bookDtos);

        ResponseEntity<GetAllBooksResponse> response =
                restTemplate.getForEntity(apiUrl, GetAllBooksResponse.class);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(bookDtos.equals(response.getBody().getDevelopmentBookListDtos()));
    }

    @Test
    void shouldPurchaseTwoBooksWithSuccess(){

        //Given
        String apiUrl = "http://localhost:" + port +"/api/books/purchase";
        DevelopmentBookPurchaseDto purchaseDto1 = new DevelopmentBookPurchaseDto(1L, 1);
        DevelopmentBookPurchaseDto purchaseDto2 = new DevelopmentBookPurchaseDto(2L, 1);
        List<DevelopmentBookPurchaseDto> purchaseDtos = new ArrayList<>();
        purchaseDtos.add(purchaseDto1);
        purchaseDtos.add(purchaseDto2);
        Integer expectedPrice = 2 * singleBookPrice - 2 * singleBookPrice * 5 / 100;

        //When
        Mockito.when(developmentBookService.purchaseBooks(Mockito.any())).thenReturn(expectedPrice);

        ResponseEntity<PurchaseBooksResponse> response =
                restTemplate.postForEntity(apiUrl, purchaseDtos,PurchaseBooksResponse.class);

        //Assert
        assertNotNull(response);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(expectedPrice, response.getBody().getTotalPrice());

    }
}