package org.calendarmanagement.dto.userDto.response;

import org.calendarmanagement.dto.userDto.SessionUser;

public record LoginResponse(
        Long id,
        String userName,
        String email
)
{
    // 로그인 response는 유저 생성일과 수정일 반환X
    public static LoginResponse of(SessionUser user) {
        return new LoginResponse(user.getId(), user.getUserName(), user.getEmail());
    }
}
