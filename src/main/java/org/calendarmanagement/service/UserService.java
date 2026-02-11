package org.calendarmanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.Exception.NoSuchUserException;
import org.calendarmanagement.Exception.PasswordMismatchException;
import org.calendarmanagement.config.PasswordEncoder;
import org.calendarmanagement.dto.userDto.request.UpdateUserRequest;
import org.calendarmanagement.dto.userDto.response.GetUserResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.dto.userDto.request.LoginRequest;
import org.calendarmanagement.dto.userDto.request.SignUpRequest;
import org.calendarmanagement.dto.userDto.response.SignUpResponse;
import org.calendarmanagement.entity.User;
import org.calendarmanagement.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    // 유저 생성
    @Transactional
    public SignUpResponse createUser(@Valid SignUpRequest request) {
        User user = new User(request.getUserName(), request.getEmail(), passwordEncoder.encode(request.getPassword()));
        User savedUser = userRepository.save(user);
        return SignUpResponse.from(savedUser);
    }

    // 로그인
    @Transactional
    public SessionUser login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.getEmail()).orElseThrow(
                () -> new NoSuchUserException("존재하지 않는 유저입니다.")
        );
        if(!passwordEncoder.matches(request.getPassword(),user.getPassword())){
            throw new PasswordMismatchException("비밀번호가 일치하지 않습니다.");
        }
        return SessionUser.from(user);
    }

    // 전체 유저 조회
    public List<GetUserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(GetUserResponse::from).toList();

    }

    // 유저 단건 조회
    public GetUserResponse getUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new NoSuchUserException("존재하지 않는 유저입니다.")
        );
        return GetUserResponse.from(user);
    }

    @Transactional
    public GetUserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new NoSuchUserException("존재하지 않는 유저입니다.")
        );
        // 더티체킹
        user.setUserName(request.getUserName());
        return GetUserResponse.from(user);
    }
}
