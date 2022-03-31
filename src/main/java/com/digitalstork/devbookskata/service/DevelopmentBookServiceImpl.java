package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.dto.DevelopmentBookPurchaseDto;
import com.digitalstork.devbookskata.exception.NoAvailableBooksException;
import com.digitalstork.devbookskata.mapper.DevelopmentBookDevelopmentBookDtoMapper;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import com.digitalstork.devbookskata.repository.DevelopmentBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DevelopmentBookServiceImpl implements DevelopmentBookService {

    private final DevelopmentBookRepository developmentBookRepository;

    private final DevelopmentBookDevelopmentBookDtoMapper mapper;

    @Override
    public List<DevelopmentBookListDto> getAllDevelopmentBooks() {
        List<DevelopmentBook> developmentBooks = developmentBookRepository.findAll();
        if (developmentBooks.isEmpty()) {
            throw new NoAvailableBooksException();
        }
        return mapper.developmentBooksToDevelopmentBookListDtos(developmentBooks);
    }

    @Override
    public Integer purchaseBooks(List<DevelopmentBookPurchaseDto> purchaseDtos) {
        return null;
    }
}
