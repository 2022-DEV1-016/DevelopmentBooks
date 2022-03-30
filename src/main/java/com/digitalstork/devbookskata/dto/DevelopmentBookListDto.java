package com.digitalstork.devbookskata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentBookListDto {

    private Long id;
    private String title;
    private String reference;
    private Integer nbAvailableCopies;

    @Override
    public boolean equals(Object obj) {
        DevelopmentBookListDto bookDto = (DevelopmentBookListDto) obj;
        return this.getId() == bookDto.getId();
    }
}
