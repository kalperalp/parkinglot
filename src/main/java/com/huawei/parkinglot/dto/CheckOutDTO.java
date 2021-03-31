package com.huawei.parkinglot.dto;


import lombok.Data;

@Data
public class CheckOutDTO {
    private String licensePlate;
    private Integer parkingAreaId;
    private Integer vehicleType;
}
