package Interfaces;

import java.io.IOException;

public interface HtmlToCsv {
    void htmlToCsv(String htmlFile, String csvFile, String semester); //reads htmlFile and converts it to CSV and writes the CSV to csvFile
}
