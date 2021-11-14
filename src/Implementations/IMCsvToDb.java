package Implementations;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import Interfaces.Course;

public class IMCsvToDb implements Interfaces.CsvToDb {
	@Override
	public void csvToDb(List<Course> courses, String csvFile) {
		BufferedReader reader;
		try {
			reader = new BufferedReader(new FileReader(csvFile));
			String line = reader.readLine();
			while (line != null) {
				List<String> r = Arrays.asList(line.split("\\s*,\\s*"));
				Course course = new IMCourse(r.get(0), r.get(1), r.get(2), r.get(3), r.get(4),
						Float.parseFloat(r.get(5)), r.get(6), Integer.parseInt(r.get(7)), Integer.parseInt(r.get(8)),
						r.get(9), r.get(10), r.get(11), r.get(12), r.get(13).equals("."), r.get(14).equals("."),
						r.get(15).equals("."), r.get(16).equals("."), r.get(17).equals("."), r.get(18).equals("."),
						r.get(19), r.get(20));

				courses.add(course);
				line = reader.readLine();

			}
			reader.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
