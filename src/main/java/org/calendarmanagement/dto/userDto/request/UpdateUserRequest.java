package org.calendarmanagement.dto.userDto.request;

import lombok.Getter;

@Getter
public class UpdateUserRequest {
    private String userName;

    public UpdateUserRequest(String userName) {
        this.userName = userName;
    }
}
