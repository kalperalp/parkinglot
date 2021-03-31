package com.huawei.parkinglot.service;


import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.exception.CapacityNotEnoughException;
import com.huawei.parkinglot.exception.VehicleNotFoundException;

/**
 * Calculations will be placed on each vehicle type
 */
public interface VehicleService {


	void checkOut(Vehicle vehicle,Integer parkingAreaId) throws VehicleNotFoundException;
	void checkIn(Vehicle vehicle,Integer parkingAreaId) throws CapacityNotEnoughException;
}
