package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.*;
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
        CreateScheduleResponse response = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);

    }

    // TODO : 일정 단건 조회 업그레이드. 단건 조회 시, 등록된 댓글들을 포함하여 응답하기
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleWithCommentsResponse> getOneSchedule(@PathVariable Long scheduleId){
        GetScheduleWithCommentsResponse response = scheduleService.getOneScheduleWithComments(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

//    @GetMapping("/schedules/{scheduleId}")
//    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId){
//        GetScheduleResponse response = scheduleService.getOneSchedule(scheduleId);
//        return ResponseEntity.status(HttpStatus.OK).body(response);
//    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllScheduleByAuthor(@RequestParam(defaultValue = "") String author){
        List<GetScheduleResponse> response = scheduleService.getSchedules(author);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<ModifyScheduleResponse> modifySchedule(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue = "") String password,
            @RequestParam(defaultValue = "") String author,
            @RequestParam(defaultValue = "") String title){
            ModifyScheduleResponse response = scheduleService.modifySchedule(scheduleId,password,author,title);
            return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @PathVariable Long scheduleId,
            @RequestParam(defaultValue="") String password){
        scheduleService.deleteSchedule(scheduleId,password);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
