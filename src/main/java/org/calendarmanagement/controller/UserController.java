package org.calendarmanagement.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.dto.userDto.request.LoginRequest;
import org.calendarmanagement.dto.userDto.request.SignUpRequest;
import org.calendarmanagement.dto.userDto.response.SignUpResponse;
import org.calendarmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    // TODO : 회원가입
    @PostMapping("/users/signUp")
    public ResponseEntity<SignUpResponse> signUp(@Valid @RequestBody SignUpRequest request) {
        SignUpResponse response = userService.createUser(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    // TODO : 로그인
    @PostMapping("/users/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest request, HttpSession session){
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser",sessionUser);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
    // TODO : 로그아웃
    // TODO : 전체 조회
    // TODO : 단건 조회
    // TODO : 수정



}
