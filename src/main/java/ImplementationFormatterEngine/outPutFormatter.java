package ImplementationFormatterEngine;

import Interfaces.Schedule;
import InterfacesformatterEngine.IoutputFormatter;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class outPutFormatter implements IoutputFormatter {

    @Override
    public String selector(TreeMap<String, List<Schedule>> methodAndList) {

        if (methodAndList.firstKey().equalsIgnoreCase("invalid date"))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase("invalid room"))
            return methodAndList.firstKey();

        if (methodAndList.firstKey().equalsIgnoreCase("invalid day of week"))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase("invalid professor name"))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase("invalid command"))
            return methodAndList.firstKey();
        
        switch (methodAndList.firstKey()) {
            case "whereisprof":
            case "whoistherenow":
            case "whowastherelast":
                return scheduleToString(methodAndList.get(methodAndList.firstKey()).get(0));
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
