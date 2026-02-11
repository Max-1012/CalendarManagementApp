package org.calendarmanagement.controller;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.userDto.request.UpdateUserRequest;
import org.calendarmanagement.dto.userDto.response.GetUserResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.dto.userDto.request.LoginRequest;
import org.calendarmanagement.dto.userDto.request.SignUpRequest;
import org.calendarmanagement.dto.userDto.response.LoginResponse;
import org.calendarmanagement.dto.userDto.response.SignUpResponse;
import org.calendarmanagement.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request, HttpSession session){
        SessionUser sessionUser = userService.login(request);
        session.setAttribute("loginUser",sessionUser);

        LoginResponse response = LoginResponse.from(sessionUser);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // TODO : 로그아웃
    @PostMapping("/users/logout")
    public ResponseEntity<Void> logout(@SessionAttribute(name="loginUser",required = false)SessionUser sessionUser,HttpSession session) {
        if(sessionUser==null){
            return ResponseEntity.badRequest().build();
        }
        session.invalidate();
        return ResponseEntity.noContent().build();

    }
    // TODO : 전체 조회
    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponse>> getAllUsers(){
        List<GetUserResponse> userList = userService.getAllUsers();
        return ResponseEntity.status(HttpStatus.OK).body(userList);
    }
    // TODO : 단건 조회
    @GetMapping("/users/{userId}")
    public ResponseEntity<GetUserResponse> getOneUser(@PathVariable Long userId){
        GetUserResponse response = userService.getUser(userId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    // TODO : 수정
    @PutMapping("/users")
    public ResponseEntity<GetUserResponse> updateUser(@SessionAttribute(name="loginUser",required = false)SessionUser sessionUser,
                                                      @RequestBody UpdateUserRequest request){
        if(sessionUser==null){
            return ResponseEntity.badRequest().build();
        }
        GetUserResponse response = userService.update(sessionUser.getId(),request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


}
