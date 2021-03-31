package com.huawei.parkinglot.service;

import com.huawei.parkinglot.dto.*;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.Price;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import com.huawei.parkinglot.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ParkingAreaService {

    private ParkingAreaRepository parkingAreaRepository;
    private PriceRepository priceRepository;
    private ParkRepository parkRepository;
    private VehicleRepository vehicleRepository;

    @Autowired
    public ParkingAreaService(ParkingAreaRepository parkingAreaRepository, PriceRepository priceRepository,
                              ParkRepository parkRepository, VehicleRepository vehicleRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.priceRepository = priceRepository;
        this.parkRepository = parkRepository;
        this.vehicleRepository = vehicleRepository;
    }


    @Transactional
    public ParkingArea createParkingArea(ParkingAreaCreateDTO createDTO) {
        List<Price> prices = new ArrayList<>();
        ParkingArea area = new ParkingArea();
        area.setCity(createDTO.getCity());
        area.setName(createDTO.getName());
        area.setCapacity(createDTO.getCapacity());
        parkingAreaRepository.save(area);
        Price price;
        for (PriceDTO p : createDTO.getPriceList()) {
            price = new Price();
            price.setLowerHour(p.getLowerHour());
            price.setUpperHour(p.getUpperHour());
            price.setHourlyPricing(p.getHourlyPricing());
            price.setParkingAreaId(area.getId());
            prices.add(price);
        }
        priceRepository.saveAll(prices);

        return parkingAreaRepository.save(area);
    }

    public List<ParkingArea> getAllParkingLot() {
        return parkingAreaRepository.findAll();
    }

    public ParkingArea getByName(String name) {
        return parkingAreaRepository.findByName(name);
    }


    public ParkingArea updateParkingArea(Integer id, ParkingAreaUpdateDTO updateDTO) {
        ParkingArea area = parkingAreaRepository.findOneById(id);
        area.setCity(updateDTO.getCity());
        area.setName(updateDTO.getName());
        area.setCapacity(updateDTO.getCapacity());
        return parkingAreaRepository.save(area);

    }

    public ParkingAreaIncomeDTO getDailyIncome(Integer parkingAreaId, Date date) {
        Date startDate = DateUtil.truncateDay(date);
        Date endDate = DateUtil.addHours(startDate);
        Double dailyIncome = parkRepository.findSumCalculatedFee(parkingAreaId, startDate, endDate);
        ParkingAreaIncomeDTO response = new ParkingAreaIncomeDTO();
        response.setDailyIncome(dailyIncome);
        return response;
    }

    public List<ParkingDetailsDTO> getVehicleDetails(String licensePlate) {
      
        return vehicleRepository.findParkingDetails(licensePlate);

    }


}
