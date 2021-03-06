package com.digitalstork.devbookskata.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookUtils {

    public static final Double singleBookPrice = 50d;

    public static final Integer twoBooksDiscount = 5;
    public static final Integer threeBooksDiscount = 10;
    public static final Integer fourBooksDiscount = 20;
    public static final Integer fiveBooksDiscount = 25;

    public static Double calculatePrice(List<Set<Long>> bookPacks) {
        if (Objects.nonNull(bookPacks)) {
            if (bookPacks.size() == 1) {
                if (bookPacks.get(0).size() == 1) {
                    return singleBookPrice;
                } else {
                    return calculatePriceWithDiscount(bookPacks.get(0).size());
                }
            } else {
                Double totalPrice = 0d;
                for (Set<Long> set : bookPacks) {
                    if (set.size() == 1) {
                        totalPrice += singleBookPrice;
                    }
                    else {
                        totalPrice += calculatePriceWithDiscount(set.size());
                    }
                }
                return totalPrice;
            }
        } else {
            return null;
        }
    }

    private static Double calculatePriceWithDiscount(Integer numberOfBooks) {
        switch (numberOfBooks) {
            case 2:
                return singleBookPrice * 2 - singleBookPrice * 2 * twoBooksDiscount / 100;
            case 3:
                return singleBookPrice * 3 - singleBookPrice * 3 * threeBooksDiscount / 100;
            case 4:
                return singleBookPrice * 4 - singleBookPrice * 4 * fourBooksDiscount / 100;
            case 5:
                return singleBookPrice * 5 - singleBookPrice * 5 * fiveBooksDiscount / 100;
            default:
                return null;
        }
    }
}
