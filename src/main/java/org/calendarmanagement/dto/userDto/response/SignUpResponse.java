package org.calendarmanagement.dto.userDto.response;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import org.calendarmanagement.dto.userDto.request.SignUpRequest;
import org.calendarmanagement.entity.User;

import java.time.LocalDateTime;

@Getter
public class SignUpResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final String password;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public SignUpResponse(Long id, String userName, String email, String password, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }

    public static SignUpResponse from(User user){
        new SignUpResponse(user.getId(), user.getUserName(), user.getEmail(),
                user.getPassword(), user.getCreatedDate(),user.getModifiedDate());
    }
}
