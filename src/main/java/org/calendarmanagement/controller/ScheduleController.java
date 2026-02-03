package org.calendarmanagement.controller;

import lombok.RequiredArgsConstructor;
import org.calendarmanagement.dto.CreateScheduleRequest;
import org.calendarmanagement.dto.CreateScheduleResponse;
import org.calendarmanagement.dto.GetScheduleResponse;
import org.calendarmanagement.service.ScheduleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<GetScheduleResponse> getOneSchedule(@PathVariable Long scheduleId){
        GetScheduleResponse response = scheduleService.getOneSchedule(scheduleId);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

}
