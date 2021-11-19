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
			if(html.contains("Fall 2021-2022(202210)")) {
				String fall = html.substring(html.indexOf("Fall 2021-2022(202210)"));
				fall = fall.substring(0, fall.indexOf("<TD>Spring 2021-2022(202220)</TD>")).trim();
//				fall = fall.substring(0, fall.indexOf("</TABLE>")).trim();
				String[] rows = fall.split("</TR>");
				for (String row : rows) {
					String[] tds = row.split("</TD>");
					line =  tds[ConstantVariables.CRN].replace("<TD>", "").trim() + "," +
							tds[ConstantVariables.SUBJECT].replace("<TD>", "").trim() + "," +
							tds[ConstantVariables.COURSE_NUM].replace("<TD>", "").trim() + "," +
							tds[ConstantVariables.SECTION].replace("<TD>", "").trim() + "," +"\"" +
							tds[ConstantVariables.TITLE].replace("<TD>", "").trim() + "\"," +
							tds[ConstantVariables.CREDIT_HOURS].replace("<TD>", "").trim() + "," +
							tds[ConstantVariables.COLLEGE].replace("<TD>", "").trim() + "," +
							tds[ConstantVariables.ACTUAL_ENROL].replace("<TD>", "").trim() + "," +
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
