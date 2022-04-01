package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.model.DevelopmentBook;

import java.util.List;

public interface DevelopmentBookService {

    List<DevelopmentBookListDto> getAllDevelopmentBooks();

    Double purchaseBooks(List<DevelopmentBookPurchaseDto> purchaseDtos);

    DevelopmentBook updateBookQuantity(Long id, Integer newQuantity);

}
