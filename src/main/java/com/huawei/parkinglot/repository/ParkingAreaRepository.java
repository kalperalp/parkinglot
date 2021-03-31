package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.ParkingArea;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface ParkingAreaRepository extends JpaRepository<ParkingArea,Integer> {
    ParkingArea findByName(String name);
    ParkingArea findOneById(Integer id);

}
