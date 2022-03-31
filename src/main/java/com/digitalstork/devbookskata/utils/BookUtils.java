package com.digitalstork.devbookskata.utils;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class BookUtils {

    public static final Integer singleBookPrice = 50;

    public static Integer calculatePrice(List<Set<Long>> bookPacks) {
        if (Objects.nonNull(bookPacks) && bookPacks.size() == 1) {
            if (bookPacks.get(0).size() == 1) {
                return singleBookPrice;
            } else {
                return null;
            }
        } else {
            return null;
        }
    }
}
