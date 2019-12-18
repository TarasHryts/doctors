package com.task.doctor.service;

import com.task.doctor.dao.DoctorDao;
import com.task.doctor.entity.Doctor;
import java.util.Optional;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DoctorServiceImpl implements DoctorService {
    private final DoctorDao doctorDao;

    @Autowired
    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }

    @Transactional
    @Override
    public Optional<Doctor> add(Doctor doctor) {
        return doctorDao.add(doctor);
    }
}
