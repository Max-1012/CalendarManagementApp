package org.calendarmanagement.dto.userDto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

@Getter
public class UpdateUserRequest {
    @NotBlank(message = "이름은 필수 입력 값입니다.")
    private String userName;

    public UpdateUserRequest(String userName) {
        this.userName = userName;
    }
}
