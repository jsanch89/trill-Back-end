package com.webstore.trill.HttpModels;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SellRequest {
    @NotNull
    private double price;
    @NotNull
    private Long userId;
}
