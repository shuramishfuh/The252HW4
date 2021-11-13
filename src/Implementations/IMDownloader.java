package Implementations;
import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

public class IMDownloader implements Interfaces.Downloader {

    /*
    * @param url  : url to download content from
    * @param file : file path to store downloaded content
    *
    * downloadHtmlToFile take a url and a file path
    * downloads html page from url and s
    * */
    public void downloadHtmlToFile(String url, String file) {
        try {
            URL urL = new URL(url);
            BufferedReader reader = new BufferedReader
                    (new InputStreamReader(urL.openStream()));

            BufferedWriter writer = new BufferedWriter
                    (new FileWriter(file));
            String line;
            while ((line = reader.readLine()) != null) {
                writer.write(line);
                writer.newLine();
            }
            reader.close();
            writer.close();
        }
        catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

/*
* downloads all courses from aub
* dynamic course scheduler
* */

    public void downloadAllHmlToFiles(){
        System.out.println("start downloading pages");
        String pre = new String("https://www-banner.aub.edu.lb/catalog/schd_");
        char[] letters = {'A','B','C','D','E','F','G','H','I','J','K','L','M',
                'N','O','P','Q','R','S','T','U','V','W','X','Y','Z',};
        for ( char a: letters) {
            downloadHtmlToFile(  pre.concat(String.valueOf(a)).concat(".htm"),
            (String.valueOf(a).concat("_courses.html")));
            System.out.println("done with " + String.valueOf(a) +"_courses");
        }
        System.out.println("Done downloading pages");
    }
}