package org.calendarmanagement.dto.userDto;

import lombok.Getter;
import org.calendarmanagement.entity.User;

@Getter
public class SessionUser {
    private final Long id;
    private final String userName;
    private final String email;

    public SessionUser(Long id, String userName, String email) {
        this.id = id;
        this.userName = userName;
        this.email = email;
    }

    public static SessionUser from(User user){
        return new SessionUser(user.getId(),
                user.getUserName(),
                user.getEmail());
    }
}