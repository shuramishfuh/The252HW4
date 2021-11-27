package ImplementationFormatterEngine;

import Implementations.IMCourSeeraFactory;
import Implementations.IMRoom;
import Implementations.Initializer;
import Interfaces.*;
import InterfacesformatterEngine.ILinker;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Linker implements ILinker {

    @Override
    public TreeMap<String, List<Schedule>> callCoursera(List<String> Ls) {

        List<Course> courses = new ArrayList<Course>();
        Initializer.listGenerator(courses);
        CourSeeraFactory csf = new IMCourSeeraFactory();
        CourSeera CS = csf.createInstance(courses);

        TreeMap<String, List<Schedule>> methodAndList = new TreeMap<>();

        String method = Ls.get(0).toLowerCase();

        switch (method) {
            case "roomschedule":
                if (Ls.size() == 3) {
                    List<Schedule> sh = CS.roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                    methodAndList.put(method, sh);
                    return methodAndList;
                }

                if (Character.isDigit(Ls.get(3).charAt(0))) {
                    try {
                        LocalDate date = LocalDate.parse(Ls.get(3));
                        List<Schedule> sh = CS
                                .roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()), date);
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        System.out.println(e);
                        return null;
                    }
                }
                try {
                    java.time.DayOfWeek day = java.time.DayOfWeek.valueOf(Ls.get(3).toUpperCase());
                    List<Schedule> sh = CS.roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()),
                            day);
                    methodAndList.put(method, sh);
                    return methodAndList;
                } catch (Exception e) {
                    System.out.println(e);
                    return null;
                }
        }
        return null;
    }
}
