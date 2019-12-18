package com.task.doctor.dao;

import com.task.doctor.entity.Doctor;
import java.util.Optional;

public interface DoctorDao {
    Optional<Doctor> add(Doctor doctor);
}
