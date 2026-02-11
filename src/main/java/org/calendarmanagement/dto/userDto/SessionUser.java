package org.calendarmanagement.dto.userDto;

import lombok.Getter;
import org.calendarmanagement.entity.User;
import org.hibernate.Session;

@Getter
public class SessionUser {
    private final Long id;
    private final String userName;
    private final String email;
    private final String password;

    public SessionUser(Long id, String userName, String email, String password) {
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.password = password;
    }

    public static SessionUser from(User user){
        return new SessionUser(user.getId(),
                user.getUserName(),
                user.getEmail(),
                user.getPassword());
    }
}