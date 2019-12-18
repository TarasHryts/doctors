package com.task.doctor.dao;

import com.task.doctor.entity.Doctor;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
//@Primary
public class DoctorDaoSpringImpl implements DoctorDao {
    private static Logger logger = Logger.getLogger(DoctorDaoSpringImpl.class);

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Optional<Doctor> add(Doctor doctor) {
        sessionFactory.getCurrentSession().save(doctor);
        logger.info(String.format("Doctor with id=%s saved in db", doctor.getNpiID()));
        return Optional.of(doctor);
    }
}

