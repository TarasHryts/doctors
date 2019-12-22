package com.task.doctor.dao;

import com.task.doctor.entity.Doctor;
import java.util.List;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
//@Primary
public class DoctorDaoSpringImpl implements DoctorDao {
    private static Logger logger = Logger.getLogger(DoctorDaoSpringImpl.class);
    private SessionFactory sessionFactory;

    @Autowired
    public DoctorDaoSpringImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public synchronized void addAll(List<Doctor> doctorList) {
        for (Doctor doctor : doctorList) {
            sessionFactory.getCurrentSession().persist(doctor);
        }
    }
}

