package ru.sfedu.SysyemOfUniversityManagment.utils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.sfedu.SysyemOfUniversityManagment.model.*;


import java.io.File;

public class HibernateUtil {
    private static SessionFactory sessionFactory;
    private static final Logger log = LogManager.getLogger(HibernateUtil.class);
    private static final String configPath = "/home/mihail/IdeaProjects/SystemOfManagment/src/main/resources/hibernate.cfg.xml";
    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            File file = new File(configPath);
            Configuration configuration = new Configuration();
            configuration.configure(file);
            configuration.addAnnotatedClass(Lection.class);
            configuration.addAnnotatedClass(PracticalTask.class);
            configuration.addAnnotatedClass(EducationalMaterial.class);
            configuration.addAnnotatedClass(Discipline.class);
            configuration.addAnnotatedClass(Teacher.class);
            configuration.addAnnotatedClass(Event.class);
            configuration.addAnnotatedClass(Exam.class);
            configuration.addAnnotatedClass(Schedule.class);
            configuration.addAnnotatedClass(StudentWork.class);
            configuration.addAnnotatedClass(Student.class);
            configuration.addAnnotatedClass(StudentGroup.class);
            ServiceRegistry serviceRegistr = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            sessionFactory = configuration.buildSessionFactory(serviceRegistr);

        }
        return sessionFactory;
    }
}