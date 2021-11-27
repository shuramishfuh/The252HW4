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

		if (Htmlfolder.listFiles().length == 0) {
			System.out.println("Starting!");
			for (char i = 'A'; i <= 'Z'; i++) {
				htmlLink = "src/Data/HTML/" + String.valueOf(i).concat(".html");
				downloader.downloadHtmlToFile(pre.concat(String.valueOf(i)).concat(".htm"), htmlLink);
				System.out.println("Page for letter " + String.valueOf(i) + " has been downloaded");
				csvMaker.htmlToCsv(htmlLink, csvFileName);
				System.out.println("Page for letter " + String.valueOf(i) + " has been parsed into csv");
			}
		} else {
			for (char i = 'A'; i <= 'Z'; i++) {
				htmlLink = "src/Data/HTML/" + String.valueOf(i).concat(".html");
				csvMaker.htmlToCsv(htmlLink, csvFileName);
				System.out.println("Page for letter " + String.valueOf(i) + " has been parsed into csv");
			}
		}

		listMaker.csvToDb(courses, csvFileName);
		System.out.println("Finished!");
	}
}
