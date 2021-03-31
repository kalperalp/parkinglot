package com.huawei.parkinglot.dto;

import lombok.Data;

import java.util.List;

@Data
public class ParkingAreaCreateDTO {


    private String name;
    private Integer capacity;
    private String city;
    private List<PriceDTO> priceList;

}
