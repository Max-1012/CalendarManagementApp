package org.calendarmanagement.dto.userDto.response;

import lombok.Getter;
import org.calendarmanagement.dto.userDto.SessionUser;

import java.time.LocalDateTime;

@Getter
public class LoginResponse {
    private final Long id;
    private final String userName;
    private final String email;


    public LoginResponse(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public static LoginResponse from(SessionUser user){
        return new LoginResponse(user.getId(), user.getUserName(), user.getEmail());
    }
}
