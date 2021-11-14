package Implementations;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.FileChannel;
import java.nio.channels.ReadableByteChannel;

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
            ReadableByteChannel readableByteChannel = Channels.newChannel(urL.openStream());
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            FileChannel fileChannel = fileOutputStream.getChannel();
            fileOutputStream.getChannel()
                    .transferFrom(readableByteChannel, 0, Long.MAX_VALUE);
        } catch (MalformedURLException e) {

            e.printStackTrace();
        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    /*
     * downloads all courses from aub
     * dynamic course scheduler
     * */

    public void downloadAllHmlToFiles() {
        System.out.println("start downloading pages");
        String pre = new String("https://www-banner.aub.edu.lb/catalog/schd_");
        char[] letters = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M',
                'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z',};
        for (char a : letters) {
            downloadHtmlToFile(pre.concat(String.valueOf(a)).concat(".htm"),
                    (String.valueOf(a).concat("_courses.html")));
            System.out.println("done with " + String.valueOf(a) + " courses");
        }
        System.out.println("Done downloading pages");
    }
}