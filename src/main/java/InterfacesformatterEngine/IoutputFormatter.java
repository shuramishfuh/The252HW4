package InterfacesformatterEngine;

import Interfaces.Schedule;

import java.util.List;
import java.util.TreeMap;

public interface IoutputFormatter {
    String selctor (TreeMap<String, List<Schedule>> methodAndList);
    String scheduleToString (List<Schedule> schedules);
    String scheduleToString (Schedule  LS);
}
