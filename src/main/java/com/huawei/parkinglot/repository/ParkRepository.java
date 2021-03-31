package com.huawei.parkinglot.repository;

import com.huawei.parkinglot.entity.Park;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface ParkRepository extends JpaRepository<Park,Integer> {


    @Query(value = "SELECT count(p.check_in) FROM park p where p.parking_area_id = :parkingAreaId and p.check_out is NULL", nativeQuery = true)
    Integer findCount(@Param("parkingAreaId") Integer parkingAreaId);

    List<Park> findByLicensePlate(String licensePlate);
    Park findByLicensePlateAndParkingAreaIdAndCheckOut(String licensePlate,Integer parkingAreaId, Date checkOut);

    @Query(value = "SELECT sum(p.fee) FROM park p where p.parking_area_id = :parkingAreaId and p.check_out >= :startDate and p.check_out < :endDate", nativeQuery = true)
    Double findSumCalculatedFee(@Param("parkingAreaId") Integer parkingAreaId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

}
