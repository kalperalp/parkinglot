package com.huawei.parkinglot.util;

public enum VehicleType {
    SUV(0),
    SEDAN(1),
    MINIVAN(2);

    private int type;

    private VehicleType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return type;
    }
}
