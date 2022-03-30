package com.digitalstork.devbookskata.mapper;

import com.digitalstork.devbookskata.dto.DevelopmentBookListDto;
import com.digitalstork.devbookskata.model.DevelopmentBook;
import org.mapstruct.Mapper;

@Mapper
public interface DevelopmentBookDevelopmentBookDtoMapper {

    DevelopmentBookListDto developmentBookToDevelopmentBookListDto(DevelopmentBook developmentBook);
}
