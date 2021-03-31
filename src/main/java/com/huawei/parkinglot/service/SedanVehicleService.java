package com.huawei.parkinglot.service;

import com.huawei.parkinglot.entity.Park;
import com.huawei.parkinglot.entity.ParkingArea;
import com.huawei.parkinglot.entity.Price;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import com.huawei.parkinglot.exception.CapacityNotEnoughException;
import com.huawei.parkinglot.repository.ParkRepository;
import com.huawei.parkinglot.repository.ParkingAreaRepository;
import com.huawei.parkinglot.repository.PriceRepository;
import com.huawei.parkinglot.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Service
public class SedanVehicleService implements VehicleService {


    private ParkingAreaRepository parkingAreaRepository;
    private ParkRepository parkRepository;
    private VehicleRepository vehicleRepository;
    private PriceRepository priceRepository;
    @Autowired
    public SedanVehicleService(ParkingAreaRepository parkingAreaRepository, ParkRepository parkRepository,
                             VehicleRepository vehicleRepository,PriceRepository priceRepository) {
        this.parkingAreaRepository = parkingAreaRepository;
        this.parkRepository = parkRepository;
        this.vehicleRepository = vehicleRepository;
        this.priceRepository = priceRepository;
    }

    @Override
    public void checkOut(Vehicle vehicle, Integer parkingAreaId) {
        Park park = parkRepository.findByLicensePlateAndParkingAreaIdAndCheckOut(vehicle.getLicensePlate(),parkingAreaId,null);
        park.setCheckOut(new Date());
        Integer totalHour
                = Math.toIntExact(TimeUnit
                .MILLISECONDS
                .toHours(park.getCheckOut().getTime() - park.getCheckIn().getTime())
                % 24);
        Price price = priceRepository.findPrice(parkingAreaId,totalHour);

        park.setFee(price.getHourlyPricing());
        parkRepository.save(park);

    }

    @Override
    public void checkIn(Vehicle vehicle, Integer parkingAreaId) throws CapacityNotEnoughException {
        ParkingArea parkingArea = parkingAreaRepository.findOneById(parkingAreaId);
        if (parkingArea.getCapacity() <= parkRepository.findCount(parkingAreaId)) {
            throw new CapacityNotEnoughException("Capacity is not enough ");
        }
        Vehicle checkVehicle = vehicleRepository.findByLicensePlate(vehicle.getLicensePlate());
        if (checkVehicle != null) {
            vehicleRepository.save(vehicle);
        }
        Park park = new Park();
        park.setCheckIn(new Date());
        park.setLicensePlate(vehicle.getLicensePlate());
        park.setParkingAreaId(parkingAreaId);
        parkRepository.save(park);
    }


}
