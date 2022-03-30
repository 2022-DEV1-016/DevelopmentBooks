package com.digitalstork.devbookskata.mapper;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface DevelopmentBookDevelopmentBookDtoMapper {

    @Mapping(target = "reference", expression = "java(new StringBuilder(\"\").append(developmentBook.getAuthor()).append(\", \").append(developmentBook.getEdition()).toString())")
    DevelopmentBookListDto developmentBookToDevelopmentBookListDto(DevelopmentBook developmentBook);

    List<DevelopmentBookListDto> developmentBooksToDevelopmentBookListDtos(List<DevelopmentBook> developmentBooks);
}
