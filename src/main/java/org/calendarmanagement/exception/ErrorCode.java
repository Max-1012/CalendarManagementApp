package org.calendarmanagement.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    // USER 관련 에러
    DUPLICATE_EMAIL(HttpStatus.BAD_REQUEST,"U001","이미 존재하는 이메일입니다."),
    DUPLICATE_USERNAME(HttpStatus.BAD_REQUEST,"U002","이미 존재하는 이름입니다."),
    NO_SUCH_USER(HttpStatus.NOT_FOUND,"U003","존재하지 않는 회원입니다."),
    MISMATCH_PASSWORD(HttpStatus.BAD_REQUEST,"U004","비밀번호가 일치하지 않습니다."),
    UNAUTHENTICATED_USER(HttpStatus.BAD_REQUEST,"U005","인증되지 않은 사용자입니다."),
    UNAUTHORIZED_USER(HttpStatus.BAD_REQUEST,"U006","권한이 없는 사용자입니다."),

    // Comment 관련 에러
    NO_SUCH_COMMENT(HttpStatus.NOT_FOUND,"C001","존재하지 않는 댓글입니다."),

    // Schedule 관련 에러
    NO_SUCH_SCHEDULE(HttpStatus.NOT_FOUND,"S001","존재하지 않는 일정입니다."),

    // 공통 에러
    INVALID_INPUT_VALUE(HttpStatus.BAD_REQUEST,"E001","잘못된 입력입니다."),
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR,"E002","서버 내부 오류가 발생했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}
