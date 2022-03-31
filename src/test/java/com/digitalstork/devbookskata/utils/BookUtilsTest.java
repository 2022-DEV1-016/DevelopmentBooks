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

    @Test
    void shouldApply10PercentDiscount_toThreeBooksPackAndReturnPrice() {
        //Given
        //a pack contains list of different book Ids
        Set<Long> threeBooksPack = new HashSet<Long>(Arrays.asList(1L, 2L, 3L));
        Integer expectedPrice = 3 * singleBookPrice -  3 * singleBookPrice * 10 / 100;

        //When
        Integer resultPrice = BookUtils.calculatePrice(Arrays.asList(threeBooksPack));

        //Assert
        assertNotNull(resultPrice);
        assertEquals(expectedPrice, resultPrice);
    }
}