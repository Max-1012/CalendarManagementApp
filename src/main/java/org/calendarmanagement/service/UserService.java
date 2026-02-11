package org.calendarmanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.Exception.NoSuchUserException;
import org.calendarmanagement.Exception.PasswordMismatchException;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.dto.userDto.request.LoginRequest;
import org.calendarmanagement.dto.userDto.request.SignUpRequest;
import org.calendarmanagement.dto.userDto.response.SignUpResponse;
import org.calendarmanagement.entity.User;
import org.calendarmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;

    @Transactional
    public SignUpResponse createUser(@Valid SignUpRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), request.getPassword());
        User savedUser = userRepository.save(user);
        return SignUpResponse.from(savedUser);
    }

    public SessionUser login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new NoSuchUserException("존재하지 않는 유저입니다.")
        );
        if(!user.getPassword().equals(request.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return SessionUser.from(user);
    }
}
