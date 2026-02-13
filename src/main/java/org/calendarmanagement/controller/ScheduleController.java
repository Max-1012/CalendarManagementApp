package org.calendarmanagement.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.scheduleDto.request.CreateScheduleRequest;
import org.calendarmanagement.dto.scheduleDto.request.UpdateScheduleRequest;
import org.calendarmanagement.dto.scheduleDto.response.CreateScheduleResponse;
import org.calendarmanagement.dto.scheduleDto.response.GetScheduleResponse;
import org.calendarmanagement.dto.scheduleDto.response.GetScheduleWithCommentsResponse;
import org.calendarmanagement.dto.scheduleDto.response.UpdateScheduleResponse;
import org.calendarmanagement.dto.userDto.SessionUser;
import org.calendarmanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> saveSchedule(
            @SessionAttribute(name = "loginUser",required = false) SessionUser sessionUser,
            @Valid @RequestBody CreateScheduleRequest request)
    {
        CreateScheduleResponse response = scheduleService.save(sessionUser,request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    // 일정 단건 조회
    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleWithCommentsResponse> getOneScheduleWithComments(@PathVariable Long scheduleId){
        GetScheduleWithCommentsResponse response = scheduleService.getOneScheduleWithComments(scheduleId);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 일정 전체 조회
    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAllSchedules(
            @RequestParam(required = false) String userName){
        List<GetScheduleResponse> response = scheduleService.getAllSchedules(userName);

        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    // 일정 수정(제목과 내용 수정 가능)
    @PatchMapping("/schedules/{scheduleId}")
    public ResponseEntity<UpdateScheduleResponse> updateSchedule(
            @SessionAttribute(name="loginUser",required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId,
            @Valid @RequestBody UpdateScheduleRequest request)
    {
        UpdateScheduleResponse response = scheduleService.modifySchedule(sessionUser,scheduleId,request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }


    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<Void> deleteSchedule(
            @SessionAttribute(name="loginUser",required = false) SessionUser sessionUser,
            @PathVariable Long scheduleId)
    {   scheduleService.deleteSchedule(sessionUser, scheduleId);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


}
