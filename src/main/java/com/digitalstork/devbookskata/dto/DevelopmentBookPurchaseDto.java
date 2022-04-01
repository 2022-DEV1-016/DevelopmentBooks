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
public class DevelopmentBookPurchaseDto {

    @Schema(description = "Book ID", example = "1", required = true)
    private Long bookId;

    @Schema(description = "Quantity to purchase", example = "4", required = true)
    private Integer quantity;
}
