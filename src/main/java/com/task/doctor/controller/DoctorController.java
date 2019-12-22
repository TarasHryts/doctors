package com.task.doctor.controller;

import com.task.doctor.service.DoctorService;
import com.task.doctor.util.ScheduledTask;
import java.util.Timer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class DoctorController {
    private final DoctorService doctorService;

    @Autowired
    public DoctorController(DoctorService doctorService) {
        this.doctorService = doctorService;
    }

    @GetMapping
    public String byDefault() {
        return "index";
    }

    @GetMapping("/save")
    public String addDoctors() {
        Timer time = new Timer();
        ScheduledTask scheduledTask = new ScheduledTask(doctorService);
        time.schedule(scheduledTask, 7 * 24 * 60 * 60 * 1000);
        return "index";
    }
}
