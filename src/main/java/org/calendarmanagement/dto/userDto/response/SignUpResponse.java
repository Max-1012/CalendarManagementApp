package org.calendarmanagement.dto.userDto.response;

import org.calendarmanagement.entity.User;

import java.time.LocalDateTime;

public record SignUpResponse(Long id, String userName, String email, LocalDateTime createdDate,
                             LocalDateTime modifiedDate) {

    public static SignUpResponse from(User user) {
        return new SignUpResponse(user.getId(), user.getUserName(), user.getEmail(),
                user.getCreatedDate(), user.getModifiedDate());
    }
}
