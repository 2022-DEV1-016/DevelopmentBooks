package com.digitalstork.devbookskata.mapper;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class DevelopmentBookDevelopmentBookDtoMapperTest {

    private DevelopmentBookDevelopmentBookDtoMapperImpl mapper =
            new DevelopmentBookDevelopmentBookDtoMapperImpl();

    @Test
    void shouldMapDevelopmentBookToDevelopmentBookListDto() {
        //given
        DevelopmentBook developmentBook = new DevelopmentBook();
        developmentBook.setId(1L);
        developmentBook.setTitle("Clean Code");
        developmentBook.setAuthor("Robert Martin");
        developmentBook.setEdition(2008);
        developmentBook.setNbAvailableCopies(5);

        //when
        DevelopmentBookListDto developmentBookListDto =
                mapper.developmentBookToDevelopmentBookListDto(developmentBook);

        //Assert
        assertNotNull(developmentBookListDto);
        assertEquals(developmentBook.getId(), developmentBookListDto.getId());
        assertEquals(developmentBook.getTitle(), developmentBookListDto.getTitle());
        assertEquals(developmentBook.getNbAvailableCopies(), developmentBookListDto.getNbAvailableCopies());
        String expectedReference = new StringBuilder("").append(developmentBook.getAuthor())
                                                        .append(", ")
                                                        .append(developmentBook.getEdition()).toString();
        assertEquals(expectedReference, developmentBookListDto.getReference());
    }

}