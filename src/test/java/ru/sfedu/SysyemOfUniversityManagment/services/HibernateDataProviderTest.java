package ru.sfedu.SysyemOfUniversityManagment.services;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import ru.sfedu.SysyemOfUniversityManagment.Constants;
import ru.sfedu.SysyemOfUniversityManagment.model.*;
import ru.sfedu.SysyemOfUniversityManagment.Constants.DayOfWeek;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class HibernateDataProviderTest {
    private static final HibernateDataProvider provider = new HibernateDataProvider();
    private static final Lection lection = new Lection("Lection for you","/home/mihail/FilesFOrSystem/Lection.txt");
    private static final PracticalTask practicalTask = new PracticalTask("/home/mihail/FilesFOrSystem/Lection.txt","Deadline 21/11/2023");
    private static final EducationalMaterial educationalMaterial = new EducationalMaterial();
    private static final Discipline math = new Discipline("Math","Exam");
    private static final Discipline english = new Discipline("English","Zachot");
    private static final Teacher teacher = new Teacher("Смерников Михаил Викторович");
    private static final Exam exam  = new Exam("702 K",12,50,DayOfWeek.FRIDAY,"math","main exam","Смерников Михаил Викторович");
    private static final Exam exam1 = new Exam("400 k",11,0,DayOfWeek.MONDAY,"eng","second exam","Иванов Иван Иванович");
    private static final Schedule schedule = new Schedule(1, Constants.TypeOfSchedule.EXAMS);
    private static final StudentWork work = new StudentWork();
    private static final Student student = new Student("Mishas");
    private static final StudentGroup studentGroup = new StudentGroup(1,"Math","12/1/1");
    private long id;

    @BeforeAll
    static void init() throws Exception {
        educationalMaterial.setLections(new ArrayList<>(List.of(lection)));
        educationalMaterial.setTasks(new ArrayList<>(List.of(practicalTask)));
        math.setEducationalMaterial(educationalMaterial);
        english.setEducationalMaterial(educationalMaterial);
        teacher.setDisciplines(new ArrayList<>(List.of(math,english)));
        schedule.appendEventToSchedule(exam);
        schedule.appendEventToSchedule(exam1);
        work.setDisciplines("math");
        work.setHomework(true);
        work.setNameOfWork("Polinoms");
        work.setFileOfWork(new File("/home/mihail/FilesFOrSystem/Lection.txt"));
        student.setStudentWorks(List.of(work));
        student.setMyGroup(studentGroup);
        studentGroup.addStudentToGroup(student);
    }

    @Test
    void testSaveObject() throws Exception {
        id = provider.saveObject(lection);

        assertEquals(lection,provider.getObjectById(id,Lection.class));

        provider.deleteObject(lection);
    }

    @Test
    void testUpdateObject() throws Exception {
        id = provider.saveObject(lection);
        lection.setInformation("sad but true");

        assertEquals("sad but true",provider.getObjectById(id, Lection.class));

        provider.deleteObject(lection);
    }

    @Test
    void testDeleteObject() throws Exception {
        id = provider.saveObject(lection);
        provider.deleteObject(lection);

        Exception exception = assertThrows(Exception.class,()->{
            provider.deleteObject(lection);
        });

        assertEquals("Record wasn't found",exception.getMessage());
    }
    @Test
    void testSaveDisciplineRecord() throws Exception {
        id = provider.saveObject(math);

        assertEquals(math,provider.getObjectById(id, Discipline.class));

        provider.deleteObject(math);
    }
    @Test
    void testUpdateDisciplineRecord() throws Exception {
        id = provider.saveObject(math);
        math.setName("English");
        provider.updateObject(math);
        Discipline tempDisc = provider.getObjectById(id, Discipline.class).get();

        assertEquals("English",tempDisc.getName());

        provider.deleteObject(math);
    }
    @Test
    void testDeleteDisciplineRecord() throws Exception {
        id = provider.saveObject(math);
        provider.deleteObject(math);

        Exception exception = assertThrows(Exception.class,()->{
            provider.deleteObject(math);
        });

        assertEquals("Record wasn't found",exception.getMessage());
    }
    @Test
    void testSaveTeacherRecord() throws Exception {
        id = provider.saveObject(teacher);

        assertEquals(teacher,provider.getObjectById(id,Teacher.class).get());


    }
    @Test
    void saveExamRecord(){
        provider.saveObject(studentGroup);
    }
}