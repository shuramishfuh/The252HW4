package Implementations;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;


public class IMHtmlToCsv implements Interfaces.HtmlToCsv {
    @Override

    public void htmlToCsv(String htmlFile, String csvFile) {

        try {
            FileWriter myWriter = new FileWriter(csvFile, true);
            String line = "";
            String html = Files.readString(Path.of(htmlFile));
			if(html.contains("Fall 2021-2022(202210)")) {
				String fall = html.substring(html.indexOf("Fall 2021-2022(202210)"));
				fall = fall.substring(0, fall.indexOf("<TD>Spring 2021-2022(202220)</TD>")).trim();
				//fall = fall.substring(0, fall.indexOf("</TABLE>")).trim();
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
