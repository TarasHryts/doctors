package com.task.doctor.util;

import com.task.doctor.entity.Doctor;
import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {
    private static Logger logger = Logger.getLogger(HibernateUtil.class);
    private static SessionFactory sessionFactory;

    private HibernateUtil() {}

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                Configuration configuration = new Configuration().configure();
                configuration.addAnnotatedClass(Doctor.class);
                StandardServiceRegistryBuilder builder =
                        new StandardServiceRegistryBuilder()
                        .applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(builder.build());
            } catch (Exception e) {
                logger.error("Error with session factory" + e);
            }
        }
        return sessionFactory;
    }
}
