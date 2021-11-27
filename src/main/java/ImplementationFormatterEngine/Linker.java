package ImplementationFormatterEngine;

import Implementations.IMCourSeeraFactory;
import Implementations.IMRoom;
import Implementations.Initializer;
import Interfaces.*;
import InterfacesformatterEngine.ILinker;
import InterfacesformatterEngine.IoutputFormatter;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.TreeMap;

public class Linker implements ILinker {

    @Override
    public TreeMap<String, List<Schedule>> callCoursera(List<String> Ls) {

        List<Course> courses = new ArrayList<Course>();
        Initializer.listGenerator(courses);
        CourSeeraFactory csf = new IMCourSeeraFactory();
        CourSeera CS = csf.createInstance(courses);

        String method = Ls.get(0).toLowerCase();
        IoutputFormatter formatter = new outPutFormatter();

        TreeMap<String, List<Schedule>> methodAndList = new TreeMap<>();

        switch (method) {
            case "roomschedule":
                if (Ls.size() == 2) {
                    return (TreeMap<String, List<Schedule>>) methodAndList.put(method, CS.roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase())));
                }

                if (Character.isDigit(Ls.get(3).charAt(0))) {
                    try {
                        LocalDate date = LocalDate.parse(Ls.get(3));
                        return (TreeMap<String, List<Schedule>>) methodAndList.put(method,CS.roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()),date));
                    } catch (Exception e) {
                        System.out.println(e);
                        return null;
                    }
                }
                try {
                    java.time.DayOfWeek day = java.time.DayOfWeek.valueOf(Ls.get(3).toUpperCase());
                    return (TreeMap<String, List<Schedule>>) methodAndList.put(method,CS.roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()),day));
                } catch (Exception e) {
                    System.out.println(e);
                    return null;
                }
        }
        return null;
    }
}
