package com.task.doctor.service;

import com.task.doctor.entity.Doctor;
import java.util.Optional;

public interface DoctorService {
    Optional<Doctor> add(Doctor doctor);
}
