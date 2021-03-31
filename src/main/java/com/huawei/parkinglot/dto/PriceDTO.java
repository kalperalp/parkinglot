package com.huawei.parkinglot.dto;

import lombok.Data;

@Data
public class PriceDTO {

    private Integer lowerHour;
    private Integer upperHour;
    private Double hourlyPricing;

}
