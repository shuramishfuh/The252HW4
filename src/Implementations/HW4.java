package Implementations;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class HW4 implements Interfaces.HW4 {
    public static void main(String[] args) {

//        uncomment to re download files

//        IMDownloader downloader = new IMDownloader();
//        downloader.downloadAllHmlToFiles();

        File myObj = new File("courses.csv");
        if (myObj.exists()) {
            myObj.delete();
        }

        IMHtmlToCsv e = new IMHtmlToCsv();
        System.out.println("file created");
        e.htmlToCsv("A_courses.html", "courses.csv");
        List<Implementations.Course> courses = new ArrayList();
//        IMCsvToDb ee = new IMCsvToDb();
//        ee.csvToDb(courses, "courses.csv");
//        System.out.println(courses.get(0).getCrn());


    }

}
