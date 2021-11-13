package Interfaces;

import java.util.List;

public interface CsvToDb {
    void csvToDb(List<Implementations.Course> courses, String csvFile);

}
