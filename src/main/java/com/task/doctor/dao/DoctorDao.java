package com.task.doctor.dao;

import com.task.doctor.entity.Doctor;
import java.util.List;

public interface DoctorDao {
    void addAll(List<Doctor> doctorList);
}
