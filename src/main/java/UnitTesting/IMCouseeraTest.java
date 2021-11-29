package UnitTesting;

import Implementations.IMCourSeeraFactory;
import Implementations.IMInstructor;
import Implementations.IMRoom;
import Implementations.Initializer;
import Interfaces.*;
import org.junit.Ignore;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class IMCouseeraTest {

    private static CourSeera CS;

    public void Initializer() {
        List<Course> courses = new ArrayList<Course>();
        Initializer.listGenerator(courses);
        CourSeeraFactory csf = new IMCourSeeraFactory();
        CS = csf.createInstance(courses);
    }

    @Test
    public void test_courseera_roomSchedule() {
        Initializer();
        Room r = new IMRoom("ADAD", "209");
        List<Schedule> sc = CS.roomSchedule(r);

        assertEquals(sc.get(0).getInstructor(), "Rima Semaan");
        assertEquals(sc.get(0).getFromTime(), LocalTime.parse("10:00"));
        assertEquals(sc.get(0).getToTime(), LocalTime.parse("11:59"));

        Room r1 = new IMRoom("nIcElY", "323");
        sc = CS.roomSchedule(r1, LocalDate.parse("2021-11-29"));

        assertEquals(sc.get(2).getInstructor(), "Mahmoud Bdeir");
        assertEquals(sc.get(2).getFromTime(), LocalTime.parse("10:00"));
        assertEquals(sc.get(2).getToTime(), LocalTime.parse("10:50"));
        assertEquals(sc.get(2).getCourse(), "CMPS 252  Section 3 \"Software Construction\"");

        Room r2 = new IMRoom("nIcElY", "211");
        sc = CS.roomSchedule(r2, DayOfWeek.TUESDAY);

        assertEquals(sc.get(1).getInstructor(), "Zahraa Yasseen");
        assertEquals(sc.get(1).getFromTime(), LocalTime.parse("11:00"));
        assertEquals(sc.get(1).getToTime(), LocalTime.parse("12:15"));
        assertEquals(sc.get(1).getCourse(), "CMPS 285  Section 1 \"Computer Graphics\"");

    }

    // @Test
    @Ignore
    // This test is time dependent
    // This test was run on Monday at 2.55pm
    public void test_courseera_whoWasThereLast() {
        Initializer();
        Room r2 = new IMRoom("nIcElY", "323");
        Schedule sc = CS.whoWasThereLast(r2);

        assertEquals(sc.getInstructor(), "Fatima Mroue");
        assertEquals(sc.getFromTime(), LocalTime.parse("14:00"));
        assertEquals(sc.getToTime(), LocalTime.parse("14:50"));
        assertEquals(sc.getCourse(), "MATH 211  Section E2 \"Discrete Mathematics\"");
    }

    //@Test
    @Ignore
    // This test is time dependent
    // This test was run on Monday at 2.55pm

    public void test_courseera_whoIsThereNow() {
        Initializer();
        Room r3 = new IMRoom("PHYS","333");

        Schedule sc = CS.whoIsThereNow(r3);
        assertEquals(sc.getInstructor(), "Muhannad Hariri");
    }

    // @Test
    @Ignore
    // This test is time dependent
    // This test was run on Monday at 2.55pm

    public void test_courseera_whereIsProfessor() {
        Initializer();
        Instructor ins = new IMInstructor("Muhannad", "Hariri");

        Schedule sc1 = CS.whereIsProf(ins);
        assertEquals(sc1.getRoom(), "PHYS 333");
    }

    // @Test
    @Ignore
    // This test is time dependent
    // This test was run on Monday at 2.55pm

    public void test_courseera_whereWillProfBe() {
        Initializer();
        Instructor ins = new IMInstructor("Muhannad", "Hariri");

        List<Schedule> sc1 = CS.whereWillProfBe(ins);
        assertEquals(sc1.get(2).getRoom(), "PHYS 333");
    }

    @Test
    public void test_courseera_profSchedule() {
        Initializer();
        Instructor is = new IMInstructor("Zeina", "Fayad");
        List<Schedule> sc = CS.profSchedule(is);

        assertEquals(sc.get(0).getRoom(), "FISK 337");
        assertEquals(sc.get(1).getRoom(), "NICELY 209");
        assertEquals(sc.get(2).getRoom(), "NICELY 411");

        assertEquals(sc.get(0).getCourse(), "CVSP 201  Section 7 \"Ancient Near East& Class Civil\"");
        assertEquals(sc.get(1).getCourse(), "CVSP 201  Section 5 \"Ancient Near East& Class Civil\"");
        assertEquals(sc.get(2).getCourse(), "CVSP 201  Section 6 \"Ancient Near East& Class Civil\"");
    }
}
