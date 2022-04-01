package com.digitalstork.devbookskata.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentBookListDto {

    @Schema(description = "Development Book ID", example = "1")
    private Long id;

    @Schema(description = "title of the book", example = "Clean Code")
    private String title;

    @Schema(description = "Author of the Book and edition year", example = "Robert Martin, 2008")
    private String reference;

    @Schema(description = "Number of available copies in stock", example = "5")
    private Integer nbAvailableCopies;

    @Override
    public boolean equals(Object obj) {
        DevelopmentBookListDto bookDto = (DevelopmentBookListDto) obj;
        return this.getId() == bookDto.getId();
    }
}
