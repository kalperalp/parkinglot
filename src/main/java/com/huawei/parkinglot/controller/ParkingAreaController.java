package com.huawei.parkinglot.controller;


import com.huawei.parkinglot.dto.ParkingAreaCreateDTO;
import com.huawei.parkinglot.dto.ParkingAreaIncomeDTO;
import com.huawei.parkinglot.dto.ParkingAreaUpdateDTO;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.service.ParkingAreaService;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/parking-area")
public class ParkingAreaController {


    private ParkingAreaService parkingAreaService;

    @Autowired
    public ParkingAreaController(ParkingAreaService parkingAreaService) {
        this.parkingAreaService = parkingAreaService;
    }

    @PostMapping("/create")
    public ParkingArea createParkingArea(@RequestBody ParkingAreaCreateDTO createDTO) {
        return parkingAreaService.createParkingArea(createDTO);
    }

    @GetMapping("/all")
    public List<ParkingArea> getAllParkingLot() {
        return parkingAreaService.getAllParkingLot();
    }

    @GetMapping("/{name}")
    public ParkingArea getByName(@PathVariable String name) {
        return parkingAreaService.getByName(name);
    }

    @PutMapping("/update")
    public ParkingArea updateParkingArea(@RequestParam Integer id,@RequestBody ParkingAreaUpdateDTO updateDTO) {
        return parkingAreaService.updateParkingArea(id,updateDTO);
    }

    @GetMapping("/income")
    public ParkingAreaIncomeDTO getDailyIncome(@RequestParam Integer parkingAreaId,@RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date date){
        return parkingAreaService.getDailyIncome(parkingAreaId,date);
    }


}
