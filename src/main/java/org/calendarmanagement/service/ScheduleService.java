package org.calendarmanagement.service;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.exception.NoSuchInstanceException;
import org.calendarmanagement.common.ExceptionHandler;
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
@Transactional
public class ScheduleService {
    private final ScheduleRepository scheduleRepository;
    private final CommentService commentService;
    private final ExceptionHandler exceptionHandler = new ExceptionHandler();

    public CreateScheduleResponse save(CreateScheduleRequest request) throws Exception{
        exceptionHandler.validateCreateScheduleRequest(request);
        // 스케쥴 생성
        Schedule newSchedule = new Schedule(request.getTitle(), request.getContent(),request.getAuthor(), request.getPassword());
        // 스케쥴 저장
        Schedule savedSchedule = scheduleRepository.save(newSchedule);

        return new CreateScheduleResponse(savedSchedule.getId(), savedSchedule.getTitle(), savedSchedule.getContent()
                , savedSchedule.getAuthor(), savedSchedule.getCreatedDate(),savedSchedule.getModifiedDate());

    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOneSchedule(Long scheduleId) throws Exception{
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchInstanceException("존재하지 않는 일정입니다.")
        );
        return new GetScheduleResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    @Transactional(readOnly = true)
    public GetScheduleWithCommentsResponse getOneScheduleWithComments(Long scheduleId) throws Exception{
        // 스케쥴 찾아오기
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchInstanceException("존재하지 않는 일정입니다.")
        );
        // 댓글 찾아오기
        List<GetCommentResponse> commentResponse =  commentService.getCommentsByScheduleId(scheduleId);

        return new GetScheduleWithCommentsResponse(schedule.getId(),schedule.getTitle(),schedule.getContent()
                ,schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate(),commentResponse);
    }


    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAllSchedules(String author) {
        List<Schedule> schedules;
        if(author==null){
            schedules = scheduleRepository.findAll().stream().toList();
        }else{
            schedules = scheduleRepository.findAll().stream().filter(
                    schedule -> schedule.getAuthor().equals(author)).toList();
        }
        return schedules.stream().map(schedule -> new GetScheduleResponse(schedule.getId(), schedule.getTitle(), schedule.getContent(),
                schedule.getAuthor(), schedule.getCreatedDate(), schedule.getModifiedDate())).toList();
    }

    public ModifyScheduleResponse modifySchedule(Long scheduleId, String password, String author, String title)throws Exception {
        Schedule schedule = exceptionHandler.validateModifyScheduleRequest(scheduleId, password, author, title, scheduleRepository);
        if(author!=null && !author.isBlank()){
            // 작성자명을 전달받은 경우 수정
            schedule.setAuthor(author);
        }
        if(title!=null && !title.isBlank()){
            // 일정 제목을 전달받은 경우 수정
            schedule.setTitle(title);
        }
        // 수정일을 수정한 시점으로 변경
        schedule.setModifiedDate(LocalDateTime.now());

        return new ModifyScheduleResponse(schedule.getId(),schedule.getTitle(), schedule.getContent(),
                schedule.getAuthor(),schedule.getCreatedDate(),schedule.getModifiedDate());
    }

    public void deleteSchedule(Long scheduleId,String password) throws Exception{
        exceptionHandler.validateDeleteScheduleRequest(scheduleId,password,scheduleRepository);
        scheduleRepository.deleteById(scheduleId);
    }
}
