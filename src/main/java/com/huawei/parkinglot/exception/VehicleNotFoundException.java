package com.huawei.parkinglot.exception;

public class VehicleNotFoundException  extends ApiException{
    private static final long serialVersionUID = -2737319632275059973L;

    public VehicleNotFoundException(String licensePlate) {
        super("Vehicle with license plate: " + licensePlate + " not found");
    }
}
