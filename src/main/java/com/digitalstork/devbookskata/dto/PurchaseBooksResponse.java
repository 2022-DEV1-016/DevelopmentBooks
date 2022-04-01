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
public class PurchaseBooksResponse {

    @Schema(description = "Price of the pack (€)", example = "95.0")
    private Double totalPrice;
}
