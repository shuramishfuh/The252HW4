package ImplementationFormatterEngine;

import Interfaces.Schedule;
import InterfacesformatterEngine.IoutputFormatter;

import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class outPutFormatter implements IoutputFormatter {

    @Override
    public String selector(TreeMap<String, List<Schedule>> methodAndList) {

        if (methodAndList.firstKey().equalsIgnoreCase(ConstantVariables.InvalidDate))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase(ConstantVariables.InvalidRoom))
            return methodAndList.firstKey();

        if (methodAndList.firstKey().equalsIgnoreCase(ConstantVariables.InvalidDayOfWeek))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase(ConstantVariables.InvalidProfName))
            return methodAndList.firstKey();
        
        if (methodAndList.firstKey().equalsIgnoreCase(ConstantVariables.InvalidCommand))
            return methodAndList.firstKey();
        
        switch (methodAndList.firstKey()) {
            case ConstantVariables.WhereIsProf:
            case ConstantVariables.WhoIsThereNow:
            case ConstantVariables.WhoWasThereLast:
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
