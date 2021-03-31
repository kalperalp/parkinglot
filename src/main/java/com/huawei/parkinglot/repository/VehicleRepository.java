package com.huawei.parkinglot.repository;


import com.huawei.parkinglot.dto.ParkingDetailsDTO;
import com.huawei.parkinglot.entity.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, String> {
    Vehicle findByLicensePlate(String licensePlate);


    @Query("SELECT new com.huawei.parkinglot.dto.ParkingDetailsDTO(p.licensePlate, pa.id, pa.city, pa.name, p.checkIn, p.checkOut, p.fee )" +
            " FROM Park p INNER JOIN ParkingArea pa on pa.id= p.parkingAreaId Where p.licensePlate = :licensePlate")
    List<ParkingDetailsDTO> findParkingDetails(@Param("licensePlate") String licensePlate);


}
