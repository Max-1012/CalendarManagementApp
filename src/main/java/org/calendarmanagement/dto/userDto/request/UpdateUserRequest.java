package org.calendarmanagement.dto.userDto.request;

import jakarta.validation.constraints.NotBlank;

public record UpdateUserRequest (
    @NotBlank(message = "이름은 필수 입력 값입니다.")
     String userName
){
    public UpdateUserRequest(String userName) {
        this.userName = userName;
    }
}
