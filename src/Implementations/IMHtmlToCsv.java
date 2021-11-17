package Implementations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class IMHtmlToCsv implements Interfaces.HtmlToCsv {
    private List<String> semesters = new ArrayList<String>(Arrays.asList("Fall","Spring"));
	@Override
    public void htmlToCsv(String htmlFile, String csvFile, String semester) {

        try {
			semesters.remove(semester);
            FileWriter myWriter = new FileWriter(csvFile, true);
            String line = "";
			String required_semester = semester.concat(" 2021-2022(202220)");
			String next_semester_regex = "<TD>".concat(semesters.get(0)).concat(" 2021-2022(202220)").concat("</TD>");
            String html = Files.readString(Path.of(htmlFile));
			if(html.contains(required_semester)) {
				String fall = html.substring(html.indexOf(required_semester));
				fall = fall.substring(0, fall.indexOf(next_semester_regex)).trim();
				String[] rows = fall.split("</TR>");
				for (String row : rows) {
					String[] tds = row.split("</TD>");
					line =  tds[1].replace("<TD>", "").trim() + "," +
							tds[2].replace("<TD>", "").trim() + "," +
							tds[3].replace("<TD>", "").trim() + "," +
							tds[4].replace("<TD>", "").trim() + "," +"\"" +
							tds[5].replace("<TD>", "").trim() + "\"," +
							tds[6].replace("<TD>", "").trim() + "," +
							tds[8].replace("<TD>", "").trim() + "," +
							tds[9].replace("<TD>", "").trim() + "," +
							tds[10].replace("<TD>", "").trim() + "," +
							tds[11].replace("<TD>", "").trim() + "," +
							tds[12].replace("<TD>", "").trim() + "," +
							tds[13].replace("<TD>", "").trim() + "," +
							tds[14].replace("<TD>", "").trim() + "," +
							tds[15].replace("<TD>", "").trim() + "," +
							tds[16].replace("<TD>", "").trim() + "," +
							tds[17].replace("<TD>", "").trim() + "," +
							tds[18].replace("<TD>", "").trim() + "," +
							tds[19].replace("<TD>", "").trim() + "," +
							tds[20].replace("<TD>", "").trim() + "," +
							tds[33].replace("<TD>", "").trim() + "," +
							tds[34].replace("<TD>", "").trim() + "\n";
							myWriter.append(line);
				}
                
            }
            myWriter.close();
        } catch (IOException e) {
            System.out.println("Error Parsing to csv");
        }

    }
}
