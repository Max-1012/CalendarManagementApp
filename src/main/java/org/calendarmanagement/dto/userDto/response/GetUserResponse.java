package org.calendarmanagement.dto.userDto.response;

import lombok.Getter;
import org.calendarmanagement.entity.User;

import java.time.LocalDateTime;

@Getter
public class GetUserResponse {
    private final Long id;
    private final String userName;
    private final String email;
    private final LocalDateTime createdDate;
    private final LocalDateTime modifiedDate;

    public GetUserResponse(Long id, String userName, String email, LocalDateTime createdDate, LocalDateTime modifiedDate) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.createdDate = createdDate;
        this.modifiedDate = modifiedDate;
    }
    public static GetUserResponse from(User user){
        return new GetUserResponse(user.getId(),user.getUserName(),user.getEmail(),
                user.getCreatedDate(),user.getModifiedDate());
    }
}
