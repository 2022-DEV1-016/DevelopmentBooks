package com.digitalstork.devbookskata.service;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import com.digitalstork.devbookskata.repository.DevelopmentBookRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class DevelopmentBookServiceImplTest {

    @InjectMocks
    private DevelopmentBookServiceImpl developmentBookService;

    @Mock
    private DevelopmentBookRepository developmentBookRepository;

    @Test
    void shouldReturnTheFiveDevelopmentBooksDtos() {
        //given
        DevelopmentBook developmentBook1 =
                new DevelopmentBook(1L, "Clean Code", "Robert Martin", 2008, 5);
        DevelopmentBook developmentBook2 =
                new DevelopmentBook(2L, "The Clean Coder", "Robert Martin", 2011, 5);
        DevelopmentBook developmentBook3 =
                new DevelopmentBook(3L, "Clean Architecture", "Robert Martin", 2017, 5);
        DevelopmentBook developmentBook4 =
                new DevelopmentBook(4L, "Test Driven Development by Example", "Kent Beck", 2003, 5);
        DevelopmentBook developmentBook5=
                new DevelopmentBook(5L, "Working Effectively With Legacy Code", "Michael C. Feathers", 2004, 5);

        List<DevelopmentBook> developmentBooks = new ArrayList<>();
        developmentBooks.add(developmentBook1);
        developmentBooks.add(developmentBook2);
        developmentBooks.add(developmentBook3);
        developmentBooks.add(developmentBook4);
        developmentBooks.add(developmentBook5);

        //when
        Mockito.when(developmentBookRepository.findAll()).thenReturn(developmentBooks);

        List<DevelopmentBookListDto> developmentBookListDtos = developmentBookService.getAllDevelopmentBooks();

        //Assert
        assertNotNull(developmentBookListDtos);
        assertTrue(developmentBooks.size() == developmentBookListDtos.size());
        assertEquals(developmentBooks.get(0).getTitle(), developmentBookListDtos.get(0).getTitle());
        assertEquals(developmentBooks.get(1).getTitle(), developmentBookListDtos.get(1).getTitle());
        assertEquals(developmentBooks.get(2).getTitle(), developmentBookListDtos.get(2).getTitle());
        assertEquals(developmentBooks.get(3).getTitle(), developmentBookListDtos.get(3).getTitle());
        assertEquals(developmentBooks.get(4).getTitle(), developmentBookListDtos.get(4).getTitle());

    }
}