package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.repository.DevelopmentBookRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class DevelopmentBookServiceImpl implements DevelopmentBookService {

    private DevelopmentBookRepository developmentBookRepository;

    @Override
    public List<DevelopmentBookListDto> getAllDevelopmentBooks() {
        return null;
    }
}
