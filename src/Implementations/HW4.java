package Implementations;

import Interfaces.*;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class HW4 implements Interfaces.HW4 {
	public static void main(String[] args) {
		List<Course> courses = new ArrayList<Course>();
		listGenerator(courses);
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

	public static void listGenerator(List<Course> courses) {
		File myObj = new File("courses.csv");
		if (myObj.exists()) {
			myObj.delete();
		}
		Downloader downloader = new IMDownloader();
		HtmlToCsv csvMaker = new IMHtmlToCsv();
		CsvToDb listMaker = new IMCsvToDb();
		String htmlLink = "";
		String csvFileName = "courses.csv";
		String pre = new String("https://www-banner.aub.edu.lb/catalog/schd_");
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', };

		System.out.println("Starting!");
		for (char a : letters) {
			htmlLink = String.valueOf(a).concat(".html");
			downloader.downloadHtmlToFile(pre.concat(String.valueOf(a)).concat(".htm"), htmlLink);
			System.out.println("Page for letter " + String.valueOf(a) + " has been downloaded");
			csvMaker.htmlToCsv(htmlLink, csvFileName);
			System.out.println("Page for letter " + String.valueOf(a) + " has been parsed into csv");
		}
		listMaker.csvToDb(courses, csvFileName);
		System.out.println("Finished!");
	}

}
