package com.digitalstork.devbookskata.utils;

import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.junit.jupiter.api.Test;

import java.util.*;

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

    @Test
    void shouldApplyAllFourDiscountTypes_onFourBookPacksRespectively_andReturnTotalPrice() {
        //Given
        Set<Long> twoBooksPack = new HashSet<Long>(Arrays.asList(1L, 2L));
        Set<Long> threeBooksPack = new HashSet<Long>(Arrays.asList(1L, 2L, 3L));
        Set<Long> fourBooksPack = new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L));
        Set<Long> fiveBooksPack = new HashSet<Long>(Arrays.asList(1L, 2L, 3L, 4L, 5L));

        List<Set<Long>> packList = new ArrayList<>();
        packList.add(twoBooksPack);
        packList.add(threeBooksPack);
        packList.add(fourBooksPack);
        packList.add(fiveBooksPack);

        Integer expectedPrice = 2 * singleBookPrice -  2 * singleBookPrice * 5 / 100
                              + 3 * singleBookPrice -  3 * singleBookPrice * 10 / 100
                              + 4 * singleBookPrice -  4 * singleBookPrice * 20 / 100
                              + 5 * singleBookPrice -  5 * singleBookPrice * 25 / 100;

        //When
        Integer resultPrice = BookUtils.calculatePrice(packList);

        //Assert
        assertNotNull(resultPrice);
        assertEquals(expectedPrice, resultPrice);
    }
}