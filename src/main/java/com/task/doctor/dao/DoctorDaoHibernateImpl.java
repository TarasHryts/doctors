package com.task.doctor.dao;

import com.task.doctor.entity.Doctor;
import com.task.doctor.util.HibernateUtil;
import java.util.Optional;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

@Repository
@Primary
public class DoctorDaoHibernateImpl implements DoctorDao {
    private static Logger logger = Logger.getLogger(DoctorDaoHibernateImpl.class);

    @Override
    public Optional<Doctor> add(Doctor doctor) {
        Transaction transaction = null;
        Session session = null;
        try {
            session = HibernateUtil.getSessionFactory().openSession();
            transaction = session.beginTransaction();
            session.save(doctor);
            transaction.commit();
        } catch (Exception e) {
            logger.info(String.format("Can't save doctor with id=%s ", doctor.getNpiID()));
            if (transaction != null) {
                transaction.rollback();
            }
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return Optional.ofNullable(doctor);
    }
}
