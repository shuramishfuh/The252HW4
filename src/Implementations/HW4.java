package Implementations;

import java.io.File;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import Interfaces.CourSeera;
import Interfaces.CourSeeraFactory;
import Interfaces.Course;
import Interfaces.CsvToDb;
import Interfaces.Room;
import Interfaces.Schedule;

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
        e.htmlToCsv("H_courses.html", "courses.csv");
        List<Course> courses = new ArrayList<Course>();
        CsvToDb ee = new IMCsvToDb();
        ee.csvToDb(courses, "courses.csv");
        
        CourSeeraFactory cccc = new IMCourSeeraFactory();
        CourSeera cc = cccc.createInstance(courses);
//        
//        for (Map.Entry<Room, List<Schedule>> entry : cc.roomSchedule().entrySet()) {
//            System.out.println("Key: " + entry.getKey().getRoomNumber() + ". Value: " + entry.getValue().get(0).getInstructor());
//       }
        
        


    }

}
