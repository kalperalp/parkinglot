package com.huawei.parkinglot.repository;


import com.huawei.parkinglot.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;



@Repository
public interface PriceRepository extends JpaRepository<Price, Integer> {


    @Query(value = "SELECT * FROM price p WHERE p.parking_area_id = :parkingAreaId and p.lower_hour  <= :totalHour and p.upper_hour > :totalHour",
            nativeQuery = true)
    Price findPrice(@Param("parkingAreaId") Integer parkingAreaId, @Param("totalHour") Integer totalHour);

}
