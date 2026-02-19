package org.calendarmanagement.dto.userDto.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginRequest (
    @NotBlank @Email(message = "이메일 형식에 맞는 입력이 필요합니다.")
    String email,
    @NotBlank @Size(min=8,max=20,message = "비밀번호는 {min}자 이상, {max}자 이하여야 합니다!")
    String password
){}
