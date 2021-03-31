package com.huawei.parkinglot.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ParkingDetailsDTO {
    private String licensePlate;
    private Integer parkingAreaId;
    private String name;
    private String city;
    private Date checkIn;
    private Date checkOut;
    private Double fee;


}

