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

        String method = Ls.get(ConstantVariables.Method).toLowerCase();

        try {
            switch (method) {
                case ConstantVariables.RoomSchedule: {
                    if (Ls.size() == 3) {
                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(ConstantVariables.RoomBuilding).toLowerCase(), Ls.get(ConstantVariables.RoomNumber).toLowerCase()));

                            if(sh.isEmpty()) throw new IllegalArgumentException();

                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = ConstantVariables.InvalidRoom;
                            methodAndList.put(error, null);
                            return methodAndList;
                        }

                    }

                    if (Character.isDigit(Ls.get(ConstantVariables.Date).charAt(0))) {
                        LocalDate date = null;

                        try {
                            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/y");
                            date = LocalDate.parse(Ls.get(ConstantVariables.Date), formatter);
                        } catch (Exception e) {
                            String error = ConstantVariables.InvalidDate;
                            methodAndList.put(error, null);
                            return methodAndList;
                        }

                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(ConstantVariables.RoomBuilding).toLowerCase(), Ls.get(ConstantVariables.RoomNumber).toLowerCase()), date);

                            if(sh.isEmpty()) throw new IllegalArgumentException();

                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = ConstantVariables.InvalidRoom;
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                    }

                    if ((Character.isLetter(Ls.get(ConstantVariables.DayofWeek).charAt(0)))) {
                        java.time.DayOfWeek day = null;
                        try {
                            day = java.time.DayOfWeek.valueOf(Ls.get(ConstantVariables.DayofWeek).toUpperCase());
                        } catch (Exception e) {
                            String error = ConstantVariables.InvalidDayOfWeek;
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                        try {
                            List<Schedule> sh = CS
                                    .roomSchedule(new IMRoom(Ls.get(ConstantVariables.RoomBuilding).toLowerCase(), Ls.get(ConstantVariables.RoomNumber).toLowerCase()), day);

                            if(sh.isEmpty()) throw new IllegalArgumentException();

                            methodAndList.put(method, sh);
                            return methodAndList;
                        } catch (Exception e) {
                            String error = ConstantVariables.InvalidRoom;
                            methodAndList.put(error, null);
                            return methodAndList;
                        }
                    }
                }

                case ConstantVariables.WhoWasThereLast: {
                    try {
                        Schedule s = CS.whoWasThereLast(new IMRoom(Ls.get(ConstantVariables.RoomBuilding).toLowerCase(), Ls.get(ConstantVariables.RoomNumber).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = ConstantVariables.InvalidRoom;
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case ConstantVariables.WhoIsThereNow: {
                    try {
                        Schedule s = CS.whoIsThereNow(new IMRoom(Ls.get(ConstantVariables.RoomBuilding).toLowerCase(), Ls.get(ConstantVariables.RoomNumber).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));

                        //if(sh.isEmpty()) throw new IllegalArgumentException();
                        
                        methodAndList.put(method, sh);
                        return methodAndList;

                    } catch (Exception e) {
                        String error = ConstantVariables.InvalidRoom;
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case ConstantVariables.ProfSchedule: {
                    try {
                        List<Schedule> sh = CS
                                .profSchedule(new IMInstructor(Ls.get(ConstantVariables.InstructorFirstName).toLowerCase(), Ls.get(ConstantVariables.InstructorLastname).toLowerCase()));

                        if(sh.isEmpty()) throw new IllegalArgumentException();

                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = ConstantVariables.InvalidProfName;
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case ConstantVariables.WhereIsProf: {
                    try {
                        Schedule s = CS.whereIsProf(new IMInstructor(Ls.get(ConstantVariables.InstructorFirstName).toLowerCase(), Ls.get(ConstantVariables.InstructorLastname).toLowerCase()));
                        List<Schedule> sh = new ArrayList<>(Arrays.asList(s));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = ConstantVariables.InvalidProfName;
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                case ConstantVariables.WhereWillProfBe: {
                    try {
                        List<Schedule> sh = CS
                                .whereWillProfBe(new IMInstructor(Ls.get(ConstantVariables.InstructorFirstName).toLowerCase(), Ls.get(ConstantVariables.InstructorLastname).toLowerCase()));
                        methodAndList.put(method, sh);
                        return methodAndList;
                    } catch (Exception e) {
                        String error = ConstantVariables.InvalidProfName;
                        methodAndList.put(error, null);
                        return methodAndList;
                    }
                }

                default:
                    String error = ConstantVariables.InvalidCommand;
                    methodAndList.put(error, null);
                    return methodAndList;
            }

        } catch (Exception e) {
            return null;
        }
    }
}
