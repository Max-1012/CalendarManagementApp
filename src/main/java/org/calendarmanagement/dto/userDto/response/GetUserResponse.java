package org.calendarmanagement.dto.userDto.response;

import org.calendarmanagement.entity.User;
import java.time.LocalDateTime;

public record GetUserResponse(Long id, String userName, String email, LocalDateTime createdDate,
                              LocalDateTime modifiedDate) {
    public static GetUserResponse of(User user) {
        return new GetUserResponse(user.getId(), user.getUserName(), user.getEmail(),
                user.getCreatedDate(), user.getModifiedDate());
    }
}
