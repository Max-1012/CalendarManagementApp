package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.exception.NoSuchInstanceException;
import org.calendarmanagement.dto.request.CreateScheduleRequest;
import org.calendarmanagement.dto.response.CreateScheduleResponse;
import org.calendarmanagement.dto.response.GetScheduleResponse;
import org.calendarmanagement.dto.response.GetScheduleWithCommentsResponse;
import org.calendarmanagement.dto.response.ModifyScheduleResponse;
import org.calendarmanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> saveSchedule(@RequestBody CreateScheduleRequest request)
    {
        CreateScheduleResponse response;
        try{
            response = scheduleService.save(request);
        }catch (Exception e){
            if(e instanceof NoSuchInstanceException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }
//
//    @GetMapping("/schedules/{scheduleId}")
//    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId){
//        GetScheduleResponse response = scheduleService.getOneSchedule(scheduleId);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    // TODO : 일정 단건 조회 업그레이드. 단건 조회 시, 등록된 댓글들을 포함하여 응답하기
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleWithCommentsResponse> getOneScheduleWithComments(@PathVariable Long scheduleId){
        GetScheduleWithCommentsResponse response ;
        try{
            response = scheduleService.getOneScheduleWithComments(scheduleId);
        }catch (Exception e){
            if(e instanceof NoSuchInstanceException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(@RequestParam(required = false) String author){
        List<GetScheduleResponse> response = scheduleService.getAllSchedules(author);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //TODO
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ModifyScheduleResponse> modifySchedule(
            @PathVariable Long scheduleId,
            @RequestParam String password,
            @RequestParam(required = false) String author,
            @RequestParam(required = false) String title){
        ModifyScheduleResponse response;
        try{
            response = scheduleService.modifySchedule(scheduleId,password,author,title);
        }catch (Exception e){
            if(e instanceof NoSuchInstanceException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam String password){
        try{
            scheduleService.deleteSchedule(scheduleId,password);
        }catch (Exception e){
            if(e instanceof NoSuchInstanceException){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }else{
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
