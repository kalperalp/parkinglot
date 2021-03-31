package com.huawei.parkinglot.controller;


import com.huawei.parkinglot.dto.CheckInDTO;
import com.huawei.parkinglot.dto.CheckOutDTO;
import com.huawei.parkinglot.dto.ParkingDetailsDTO;
import com.huawei.parkinglot.entity.vehicle.Minivan;
import com.huawei.parkinglot.entity.vehicle.SUV;
import com.huawei.parkinglot.entity.vehicle.Sedan;
import com.huawei.parkinglot.exception.CapacityNotEnoughException;
import com.huawei.parkinglot.exception.VehicleNotFoundException;
import com.huawei.parkinglot.service.*;
import com.huawei.parkinglot.util.VehicleType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/park")
public class ParkController {

    private SedanVehicleService sedanVehicleService;
    private SuvVehicleService suvVehicleService;
    private MinivanVehicleService minivanVehicleService;
    private ParkingAreaService parkingAreaService;

    @Autowired
    public ParkController(SedanVehicleService sedanVehicleService, SuvVehicleService suvVehicleService,
                          MinivanVehicleService minivanVehicleService,ParkingAreaService parkingAreaService) {
        this.sedanVehicleService = sedanVehicleService;
        this.suvVehicleService = suvVehicleService;
        this.minivanVehicleService = minivanVehicleService;
        this.parkingAreaService = parkingAreaService;
    }


    @PostMapping("/check-in")
    public void checkIn(@RequestBody CheckInDTO checkInDTO) throws CapacityNotEnoughException {
        if (checkInDTO.getVehicleType() == VehicleType.SUV.getStatus()) {
            SUV suv = new SUV();
            suv.setLicensePlate(checkInDTO.getLicensePlate());
            suv.setVehicleType(checkInDTO.getVehicleType());
            suvVehicleService.checkIn(suv, checkInDTO.getParkingAreaId());
        } else if (checkInDTO.getVehicleType() == VehicleType.MINIVAN.getStatus()) {
            Minivan minivan = new Minivan();
            minivan.setLicensePlate(checkInDTO.getLicensePlate());
            minivan.setVehicleType(checkInDTO.getVehicleType());
            minivanVehicleService.checkIn(minivan, checkInDTO.getParkingAreaId());
        } else {
            Sedan sedan = new Sedan();
            sedan.setLicensePlate(checkInDTO.getLicensePlate());
            sedan.setVehicleType(checkInDTO.getVehicleType());
            sedanVehicleService.checkIn(sedan, checkInDTO.getParkingAreaId());
        }

    }


    @PostMapping("/check-out")
    public void checkOut(@RequestBody CheckOutDTO checkOutDTO) throws VehicleNotFoundException {
        if (checkOutDTO.getVehicleType() == VehicleType.SUV.getStatus()) {
            SUV suv = new SUV();
            suv.setLicensePlate(checkOutDTO.getLicensePlate());
            suv.setVehicleType(checkOutDTO.getVehicleType());
            suvVehicleService.checkOut(suv, checkOutDTO.getParkingAreaId());
        } else if (checkOutDTO.getVehicleType() == VehicleType.MINIVAN.getStatus()) {
            Minivan minivan = new Minivan();
            minivan.setLicensePlate(checkOutDTO.getLicensePlate());
            minivan.setVehicleType(checkOutDTO.getVehicleType());
            minivanVehicleService.checkOut(minivan, checkOutDTO.getParkingAreaId());
        } else {
            Sedan sedan = new Sedan();
            sedan.setLicensePlate(checkOutDTO.getLicensePlate());
            sedan.setVehicleType(checkOutDTO.getVehicleType());
            sedanVehicleService.checkOut(sedan, checkOutDTO.getParkingAreaId());
        }

    }

    @GetMapping("/park-details")
    public List<ParkingDetailsDTO>  getVehicleDetails(@RequestParam String licensePlate){
        return parkingAreaService.getVehicleDetails(licensePlate);
    }

}
