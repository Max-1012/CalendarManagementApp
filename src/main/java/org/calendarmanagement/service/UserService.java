package org.calendarmanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.exception.ErrorCode;
import org.calendarmanagement.exception.UserException;
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
        boolean existence = userRepository.existsByUserName(request.userName());
        if(existence){throw new UserException(ErrorCode.DUPLICATE_USERNAME);}

        existence = userRepository.existsByEmail(request.email());
        if(existence){throw new UserException(ErrorCode.DUPLICATE_EMAIL);}

        User user = new User(request.userName(), request.email(), passwordEncoder.encode(request.password()));
        User savedUser = userRepository.save(user);
        return SignUpResponse.of(savedUser);
    }


    // 로그인
    @Transactional
    public SessionUser login(@Valid LoginRequest request) {
        User user = userRepository.findByEmail(request.email()).orElseThrow(
                () -> new UserException(ErrorCode.NO_SUCH_USER)
        );
        if(!passwordEncoder.matches(request.password(),user.getPassword())){
            throw new UserException(ErrorCode.MISMATCH_PASSWORD);
        }
        return SessionUser.from(user);
    }

    // 전체 유저 조회
    public List<GetUserResponse> getAllUsers() {
        return userRepository.findAll().stream().map(GetUserResponse::of).toList();

    }

    // 유저 단건 조회
    public GetUserResponse getOneUser(Long userId) {
        User user = userRepository.findById(userId).orElseThrow(
                () -> new UserException(ErrorCode.NO_SUCH_USER)
        );
        return GetUserResponse.of(user);
    }

    // 유저 이름 업데이트
    @Transactional
    public GetUserResponse update(Long id, UpdateUserRequest request) {
        User user = userRepository.findById(id).orElseThrow(
                () -> new UserException(ErrorCode.NO_SUCH_USER)
        );
        // 더티체킹
        user.setUserName(request.userName());
        return GetUserResponse.of(user);
    }

    // 유저 탈퇴
    @Transactional
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getUserById(Long userId){
        return userRepository.findById(userId).orElseThrow(
                () -> new UserException(ErrorCode.NO_SUCH_USER)
        );
    }
}
