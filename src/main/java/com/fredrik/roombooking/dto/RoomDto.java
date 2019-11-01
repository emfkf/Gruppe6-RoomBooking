package com.fredrik.roombooking.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomDto {

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

    public RoomDto() {

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

}

