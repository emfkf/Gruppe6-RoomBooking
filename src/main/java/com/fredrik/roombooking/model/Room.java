package com.fredrik.roombooking.model;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    @Size(min=1, max=50)
    private String building;
    @NotNull
    @Size(min=1, max=3)
    private String floor;
    @NotNull
    @Size(min=1, max=3)
    private String number;
    @NotNull
    @Min(1)
    @Max(999)
    private int capacity;
    @Column(name = "times_booked")
    private int timesBooked;

    public Room() {

    }

    public Room(String building, String floor, String number, int capacity) {
        this.building = building;
        this.floor = floor;
        this.number = number;
        this.capacity = capacity;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getTimesBooked() {
        return timesBooked;
    }

    public void setTimesBooked(int timesBooked) {
        this.timesBooked = timesBooked;
    }

}