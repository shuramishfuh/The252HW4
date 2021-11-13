package Implementations;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;

public class HW4 implements Interfaces.HW4 {
    public static void main(String[] args) throws IOException {
        IMDownloader e = new IMDownloader();
        e.downloadAllHmlToFiles();
    }
}
