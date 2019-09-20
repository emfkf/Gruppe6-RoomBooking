package com.fredrik.roombooking.dto;

import javax.validation.constraints.NotNull;

public class BookingDto {

    @NotNull
    private Long userId;
    @NotNull
    private Long roomId;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getRoomId() {
        return roomId;
    }

    public void setRoomId(Long roomId) {
        this.roomId = roomId;
    }

}
