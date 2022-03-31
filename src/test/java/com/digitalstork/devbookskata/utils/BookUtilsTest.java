package com.digitalstork.devbookskata.utils;

import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class BookUtilsTest {

    private final Integer singleBookPrice = 50;

    @Test
    void shouldReturn50EurosForOneBook() {
        //Given
        DevelopmentBook developmentBook =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        Set<Long> singleBookPack = new HashSet<Long>(Collections.singleton(developmentBook.getId()));

        //When
        Integer resultPrice = BookUtils.calculatePrice(Arrays.asList(singleBookPack));

        //Assert
        assertNotNull(resultPrice);
        assertEquals(singleBookPrice, resultPrice);
    }
}