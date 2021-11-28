package ImplementationFormatterEngine;

import Implementations.IMCourSeeraFactory;
import Implementations.IMInstructor;
import Implementations.IMRoom;
import Implementations.Initializer;
import Interfaces.*;
import InterfacesformatterEngine.ILinker;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
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

        try {
            switch (method) {
                case "roomschedule": {
                    if (Ls.size() == 3) {
                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = "Invalid Room";
                            methodAndList.put(error, null);
                            return methodAndList;
                        }

                    }

                    if (Character.isDigit(Ls.get(3).charAt(0))) {
                        LocalDate date = null;

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
                            date = LocalDate.parse(Ls.get(3), formatter);
                        } catch (Exception e) {
                            String error = "Invalid Date";
                            methodAndList.put(error, null);
                            return methodAndList;
                        }

                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()), date);
                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = "Invalid Room";
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                    }

                    if ((Character.isLetter(Ls.get(3).charAt(0)))) {
                        java.time.DayOfWeek day = null;
                        try {
                            day = java.time.DayOfWeek.valueOf(Ls.get(3).toUpperCase());
                        } catch (Exception e) {
                            String error = "Invalid Day of Week";
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()), day);
                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = "Invalid Room";
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                    }
                }

                case "whowastherelast": {
                    try {
                        Schedule s = CS.whoWasThereLast(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = "Invalid Room";
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case "whoistherenow": {
                    try {
                        Schedule s = CS.whoIsThereNow(new IMRoom(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));
                        methodAndList.put(method, sh);
                        return methodAndList;

                    } catch (Exception e) {
                        String error = "Invalid Room";
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case "profschedule": {
                    try {
                        List<Schedule> sh = CS
                                .profSchedule(new IMInstructor(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = "Invalid Professor Name";
                        methodAndList.put(error, null);
                        return methodAndList;
                    }

                }

                case "whereisprof": {
                    try {
                        Schedule s = CS.whereIsProf(new IMInstructor(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = "Invalid Professor Name";
                        methodAndList.put(error, null);
                        return methodAndList;
                    }

                }

                case "wherewillprofbe": {
                    try {
                        List<Schedule> sh = CS
                                .whereWillProfBe(new IMInstructor(Ls.get(1).toLowerCase(), Ls.get(2).toLowerCase()));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = "Invalid Professor Name";
                        methodAndList.put(error, null);
                        return methodAndList;
                    }

                }

                default:
                    String error = "Invalid Command";
                    methodAndList.put(error, null);
                    return methodAndList;
            }

        } catch (Exception e) {
            return null;
        }
    }
}
