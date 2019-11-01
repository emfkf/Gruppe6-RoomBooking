package com.fredrik.roombooking.dto;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class RoomRangeDto {

    @NotNull
    @Size(min=1, max=50)
    private String building;
    @NotNull
    @Size(min=1, max=3)
    private String floor;
    @NotNull
    @Min(1)
    @Max(999)
    private int startNumber;
    @NotNull
    @Min(1)
    @Max(999)
    private int endNumber;
    @NotNull
    @Min(1)
    @Max(999)
    private int capacity;

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

    public int getStartNumber() {
        return startNumber;
    }

    public void setStartNumber(int startNumber) {
        this.startNumber = startNumber;
    }

    public int getEndNumber() {
        return endNumber;
    }

    public void setEndNumber(int endNumber) {
        this.endNumber = endNumber;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
