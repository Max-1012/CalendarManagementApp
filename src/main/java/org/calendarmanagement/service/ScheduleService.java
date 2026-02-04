package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.request.CreateScheduleRequest;
import org.calendarmanagement.dto.response.*;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.repository.ScheduleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        if(request.getAuthor()==null || request.getTitle()==null || request.getPassword()==null){
            throw new IllegalStateException("일정 제목, 작성자명, 비밀번호는 필수입니다.");
        }
        // 스케쥴 생성
        Schedule newSchedule = new Schedule(request.getTitle(), request.getContent(),request.getAuthor(), request.getPassword());
        // 스케쥴 저장
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent()
                , savedSchedule.getAuthor(), savedSchedule.getCreatedDate(),savedSchedule.getModifiedDate());

    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) {

        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    //TODO
    @Transactional(readOnly = true)
    public GetScheduleWithCommentsResponse getOneScheduleWithComments(Long scheduleId) {
        // 스케쥴 찾아오기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다.")
        );
        // 댓글 찾아오기
        List<GetCommentResponse> commentResponse =  commentService.getCommentsByScheduleId(scheduleId);

        return new GetScheduleWithCommentsResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate(),commentResponse);
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream().map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getAuthor(), schedule.getCreatedDate(), schedule.getModifiedDate())).toList();
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getSchedulesByAuthor(String author) {
        List<Schedule> schedules = scheduleRepository.findAll().stream().filter(
                    schedule -> schedule.getAuthor().equals(author)).toList();

        return schedules.stream().map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getAuthor(), schedule.getCreatedDate(), schedule.getModifiedDate())).toList();
    }

    // TODO : 예외처리
    @Transactional
    public ModifyScheduleResponse modifySchedule(Long scheduleId, String password, String author, String title) {
        // 더티체킹. 1. 영속상태
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다")
        );
        // 비밀번호 체크
        if(password.isEmpty()) {
            throw new IllegalStateException("비밀번호를 입력해주세요");
        }else if(!schedule.getPassword().equals(password)){
            throw new IllegalStateException("비밀번호가 틀립니다");
        }
        // 비밀번호가 일치하면
        if(author.isEmpty() && title.isEmpty()){
            throw new IllegalStateException("수정할 내용이 없습니다");
        }
        if(!author.isEmpty()){
            // 작성자명을 전달받은 경우 수정
            schedule.setAuthor(author);
        }
        if(!title.isEmpty()){
            // 일정 제목을 전달받은 경우 수정
            schedule.setTitle(title);
        }
        // 수정일을 수정한 시점으로 변경
        schedule.setModifiedDate(LocalDateTime.now());

        return new ModifyScheduleResponse(schedule.getId(),schedule.getTitle(), schedule.getContent(),
                schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    public void deleteSchedule(Long scheduleId,String password) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalStateException("존재하지 않는 일정입니다")
        );
        if(password.isEmpty()){
            throw new IllegalStateException("비밀번호를 입력해주세요");
        }
        if(!schedule.getPassword().equals(password)){
            throw new IllegalStateException("비밀번호가 틀립니다");
        }
        scheduleRepository.deleteById(scheduleId);
    }
}
