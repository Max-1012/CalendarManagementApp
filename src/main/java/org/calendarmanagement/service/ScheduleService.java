package org.calendarmanagement.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.Exception.NoSuchScheduleException;
import org.calendarmanagement.Exception.UnAuthorizedUserException;
import org.calendarmanagement.dto.commentDto.response.GetCommentResponse;
import org.calendarmanagement.dto.scheduleDto.request.CreateScheduleRequest;
import org.calendarmanagement.dto.scheduleDto.request.ModifyScheduleRequest;
import org.calendarmanagement.dto.scheduleDto.response.CreateScheduleResponse;
import org.calendarmanagement.dto.scheduleDto.response.GetScheduleResponse;
import org.calendarmanagement.dto.scheduleDto.response.GetScheduleWithCommentsResponse;
import org.calendarmanagement.dto.scheduleDto.response.ModifyScheduleResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.entity.User;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;
    private final UserService userService;

    // 일정 생성
    @Transactional
    public CreateScheduleResponse save(SessionUser sessionUser, CreateScheduleRequest request){
        // 유저 확인
        User user = userService.getUserById(sessionUser.getId());
        // 일정 생성
        Schedule newSchedule = new Schedule(user, request.getTitle(), request.getContent());
        // 일정 저장
        Schedule savedSchedule = scheduleRepository.save(newSchedule);
        return CreateScheduleResponse.from(user,savedSchedule);
    }

    // 일정 단건 조회
    public GetScheduleWithCommentsResponse getOneScheduleWithComments(Long scheduleId){
        // 일정 찾아오기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchScheduleException("존재하지 않는 일정입니다.")
        );
        // 댓글 찾아오기
        List<GetCommentResponse> commentList =  commentService.getCommentsByScheduleId(scheduleId);

        return GetScheduleWithCommentsResponse.from(schedule,commentList);
    }

    // 일정 전체 조회(수정일 내림차순)
    public List<GetScheduleResponse> getAllSchedules(String userName) {
        List<Schedule> schedules;
        if(userName==null){
            schedules = scheduleRepository.findAll();
        }else {
            schedules = scheduleRepository.findAllByUserName(userName);
        }
        return schedules.stream()
                .sorted(Comparator.comparing(Schedule::getModifiedDate).reversed())
                .map(GetScheduleResponse::from).toList();
    }

    // 일정 수정
    public ModifyScheduleResponse modifySchedule(
            SessionUser sessionUser, Long scheduleId, @Valid ModifyScheduleRequest request) {
        // 로그인 한 유저와 일정 작성 유저가 일치하는지 확인
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchScheduleException("존재하지 않는 일정입니다.")
        );
        if(!Objects.equals(sessionUser.getId(), schedule.getUser().getId())){
                throw new UnAuthorizedUserException("권한이 없는 유저입니다.");
        }
        // 수정할 값이 들어오면 수정
        if(request.getTitle()!=null){
            schedule.setTitle(request.getTitle());
        }
        if(request.getContent()!=null){
            schedule.setContent(request.getContent());
        }
        schedule.setModifiedDate(LocalDateTime.now());
        return ModifyScheduleResponse.from(schedule);
    }


    public void deleteSchedule(SessionUser sessionUser, Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchScheduleException("존재하지 않는 일정입니다.")
        );
        // 로그인 한 유저와 일정 작성 유저가 일치하는지 확인
        if(!Objects.equals(sessionUser.getId(), schedule.getUser().getId())){
            throw new UnAuthorizedUserException("권한이 없는 유저입니다.");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
