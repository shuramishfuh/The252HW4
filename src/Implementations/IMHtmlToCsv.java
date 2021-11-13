package Implementations;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;

public class IMHtmlToCsv implements Interfaces.HtmlToCsv {
	@Override
	public void htmlToCsv(String htmlFile, String csvFile) {
		try {
			String html = Files.readString(Path.of(htmlFile));
			String fall = html.substring(html.indexOf("Fall 2021-2022(202210)"));
			fall = fall.substring(0, fall.indexOf("</TABLE>")).trim();
			String[] rows = fall.split("</TR>");
			for (String row : rows) {
				String[] tds = row.split("</TD>");
				csvFile +=  tds[1].replace("<TD>", "").trim() + "," +
							tds[2].replace("<TD>", "").trim() + "," +
							tds[3].replace("<TD>", "").trim() + "," +
							tds[4].replace("<TD>", "").trim() + "," +
							tds[5].replace("<TD>", "").trim() + "," +
							tds[7].replace("<TD>", "").trim() + "," +
							tds[12].replace("<TD>", "").trim() + "," +
							tds[13].replace("<TD>", "").trim() + "," +
							tds[20].replace("<TD>", "").trim() + "," +
							tds[21].replace("<TD>", "").trim() + "\n";
			}
		}catch(IOException e) {
			System.out.println("Error Parsing to csv");
		}
			
	}
}
