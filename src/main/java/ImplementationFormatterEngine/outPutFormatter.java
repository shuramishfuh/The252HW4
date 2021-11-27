package ImplementationFormatterEngine;

import Interfaces.Schedule;
import InterfacesformatterEngine.IoutputFormatter;

import java.util.List;
import java.util.Locale;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class outPutFormatter implements IoutputFormatter {

    @Override
    public String selctor(TreeMap<String, List<Schedule>> methodAndList) {
        switch (methodAndList.firstKey()) {
            case "whereisprof":
            case "whoIstherenow":
            case "whoWastherelast":
                return scheduleToString(methodAndList.get(methodAndList.firstKey()));
            default:
                return scheduleToString(
                        methodAndList.values().stream().flatMap(List::stream).collect(Collectors.toList()));

        }
    }

    @Override
    public String scheduleToString(List<Schedule> schedules) {
        String message = "";
        for (Schedule s : schedules) {
            message += "\n" + s.getInstructor() + " " + s.getCourse() + " " + s.getFromTime() + " " + s.getToTime()
                    + " " + s.getRoom() + " " + s.getDay() + " \n";
        }
        return message;
    }

    @Override
    public String scheduleToString(Schedule LS) {
        return LS.getInstructor() + " " + LS.getCourse();
    }
}
