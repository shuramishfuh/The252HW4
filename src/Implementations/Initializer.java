package Implementations;

import java.io.File;
import java.util.List;

import Interfaces.*;

public class Initializer {
    public static void listGenerator(List<Course> courses) {
        File folder = new File("src/Data");
        if (!folder.exists()) {
			folder.mkdirs();
		}
        File Htmlfolder = new File("src/Data/HTML/");
        if (!Htmlfolder.exists()) {
			Htmlfolder.mkdirs();
		}
		File myObj = new File("src/Data/courses.csv");
		if (myObj.exists()) {
			myObj.delete();
		}
		Downloader downloader = new IMDownloader();
		HtmlToCsv csvMaker = new IMHtmlToCsv();
		CsvToDb listMaker = new IMCsvToDb();
		String htmlLink = "";
		String csvFileName = "src/Data/courses.csv";
		String pre = new String("https://www-banner.aub.edu.lb/catalog/schd_");
		char[] letters = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
				'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', };

		System.out.println("Starting!");
		for (char a : letters) {
			htmlLink = "src/Data/HTML/" + String.valueOf(a).concat(".html");
			downloader.downloadHtmlToFile(pre.concat(String.valueOf(a)).concat(".htm"), htmlLink);
			System.out.println("Page for letter " + String.valueOf(a) + " has been downloaded");
			csvMaker.htmlToCsv(htmlLink, csvFileName, "Fall");
			System.out.println("Page for letter " + String.valueOf(a) + " has been parsed into csv");
		}
		listMaker.csvToDb(courses, csvFileName);
		System.out.println("Finished!");
	}
}
