package Implementations;

import Interfaces.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HW4 implements Interfaces.HW4 {
	public static void main(String[] args) {
		List<Course> courses = new ArrayList<Course>();
		Initializer.listGenerator(courses);
		CourSeeraFactory csf = new IMCourSeeraFactory();
		CourSeera CS = csf.createInstance(courses);

		// IMInstructor imInstructor = new IMInstructor("Michel"," Kazan");
		// IMRoom room = new IMRoom("PHYS","204");
		// IMRoom room = new IMRoom("BIOL","SLH");

		// Schedule s= CS.whoWasThereLast(room);
		// List<Schedule> sc= CS.roomSchedule(room);
		// List<Schedule> sc= CS.whereWillProfBe(imInstructor);
		// List<Schedule> sc = CS.profSchedule(imInstructor);
		// Schedule s = CS.whereIsProf(imInstructor);
		// Schedule s = CS.whoIsThereNow(room);
		// System.out.println(sc);
		// for (Schedule s : sc
		// ) {
		// System.out.println(s.getInstructor());
		// System.out.println(s.getCourse());
		// System.out.println(s.getFromTime());
		// System.out.println(s.getToTime());
		// System.out.println(s.getRoom());
		// System.out.println(s.getDay());
		// }

		// for (Room room : CS.roomSchedule().keySet()) {
		// List<Schedule> a = CS.roomSchedule().get(room);
		// for (Schedule s: a
		// ) {
		// System.out.println(s.getInstructor());
		// System.out.println(s.getCourse());
		// System.out.println(s.getRoom());
		// System.out.println(s.getFromTime());
		// System.out.println(s.getToTime());
		// System.out.println(s.getDay());
		// System.out.println();
		// System.out.println();
		// System.out.println();
		// }
		// }
	}
}
