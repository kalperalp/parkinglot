package com.huawei.parkinglot.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ParkingAreaUpdateDTO {
    private String name;
    private Integer capacity;
    private String city;
}
