package org.calendarmanagement.common;

import org.calendarmanagement.Exception.InvalidInputException;
import org.calendarmanagement.Exception.InvalidRequestException;
import org.calendarmanagement.Exception.NoSuchInstanceException;
import org.calendarmanagement.dto.request.CreateCommentRequest;
import org.calendarmanagement.dto.request.CreateScheduleRequest;
import org.calendarmanagement.entity.Schedule;
import org.calendarmanagement.repository.CommentRepository;
import org.calendarmanagement.repository.ScheduleRepository;

import java.util.NoSuchElementException;

public class ExceptionHandler {
    public void validateCommentRequest(Long scheduleId, CreateCommentRequest request,
                                          CommentRepository commentRepository,
                                          ScheduleRepository scheduleRepository) throws Exception{
        boolean existence = scheduleRepository.existsById(scheduleId);
        if(!existence){
            throw new NoSuchInstanceException("존재하지 않는 일정에 댓글을 달 수 없습니다.");
        }
        if(request.getAuthor()==null || request.getContent()==null || request.getPassword()==null){
            throw new InvalidInputException("댓글 내용, 작성자명, 비밀번호는 필수입니다.");
        }
        long count = commentRepository.findAll().stream().filter(comment -> comment.getScheduleId().equals(scheduleId)).count();
        if(count>=10){
            throw new InvalidRequestException("댓글 최대 개수 10개를 초과하여 댓글을 달 수 없습니다.");
        }
        if(request.getContent().length()>100){
            throw new InvalidInputException("댓글은 100자 이내로 입력해주세요");
        }
    }

    public void validateCreateScheduleRequest(CreateScheduleRequest request) throws Exception{
        if(request.getAuthor()==null || request.getTitle()==null || request.getPassword()==null){
            System.out.println("예외1");
            throw new InvalidInputException("일정 제목, 작성자명, 비밀번호는 필수입니다");
        }
        if(request.getTitle().length()>30){
            throw new InvalidInputException("일정 제목은 30자 이내로 입력해주세요");
        }
        if(request.getContent().length()>200){
            throw new InvalidInputException("일정 내용은 200자 이내로 입력해주세요");
        }
    }

    public Schedule validateModifyScheduleRequest(Long scheduleId,String password, String author,
                                              String title,ScheduleRepository scheduleRepository)throws Exception{
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchInstanceException("존재하지 않는 일정입니다")
        );
        passwordCheck(schedule,password);
        if(author==null && title==null){
            throw new InvalidInputException("수정할 내용이 없습니다");
        }
        if(title.length()>30){
            throw new InvalidInputException("일정 제목은 30자 이내로 입력해주세요");
        }
        return schedule;
    }

    public void validateDeleteScheduleRequest(Long scheduleId, String password,ScheduleRepository scheduleRepository)throws Exception{
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new NoSuchInstanceException("존재하지 않는 일정입니다")
        );
        passwordCheck(schedule,password);
    }

    public void passwordCheck(Schedule schedule,String password)throws Exception{
        // 비밀번호 체크
        if(!schedule.getPassword().equals(password)){
            throw new InvalidInputException("비밀번호가 틀립니다");
        }
    }
}
