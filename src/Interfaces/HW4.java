package Interfaces;

import Implimentations.IMDownloader;

import java.time.LocalDate;
import java.time.LocalTime;

public interface HW4 {
    public static void main(String[] args) {
        var t1 = LocalTime.parse("14:52");
        var d = LocalDate.parse("1999-01-22");
        IMDownloader downloader = new IMDownloader();
//        downloader.downloadHtmlToFile("https://www-banner.aub.edu.lb/catalog/schd_A.htm","d.txt");
        downloader.downloadAllHmlToFiles();
    }

}
