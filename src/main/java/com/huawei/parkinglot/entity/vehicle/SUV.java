package com.huawei.parkinglot.entity.vehicle;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Table;


@Entity
@Table(name = "vehicle")
//TODO define inheritance
public class SUV extends Vehicle {

}
