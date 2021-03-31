package com.huawei.parkinglot.dto;

import lombok.Data;



@Data
public class CheckInDTO {
    private String licensePlate;
    private Integer parkingAreaId;
    private Integer vehicleType;

}
