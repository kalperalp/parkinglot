package com.huawei.parkinglot.exception;

public class CapacityNotEnoughException extends ApiException {
    private static final long serialVersionUID = -5258959358527382145L;

    public CapacityNotEnoughException(String message) {
        super(message);
    }
}
