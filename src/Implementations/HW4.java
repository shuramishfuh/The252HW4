package Implementations;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import Interfaces.Course;
import Interfaces.CsvToDb;

public class HW4 implements Interfaces.HW4 {
    public static void main(String[] args) {

        //new IMDownloader().downloadAllHmlToFiles();
        File myObj = new File("courses.csv");
        if (myObj.exists()) {
            myObj.delete();
        }

        IMHtmlToCsv e = new IMHtmlToCsv();
        System.out.println("file created");
        e.htmlToCsv("H_courses.html", "courses.csv");
        List<Course> courses = new ArrayList<Course>();
        CsvToDb ee = new IMCsvToDb();
        ee.csvToDb(courses, "courses.csv");
    }
}
