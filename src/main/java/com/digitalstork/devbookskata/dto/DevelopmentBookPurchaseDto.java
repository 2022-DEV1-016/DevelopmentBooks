package com.digitalstork.devbookskata.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DevelopmentBookPurchaseDto {

    private Long bookId;
    private Integer quantity;
}
