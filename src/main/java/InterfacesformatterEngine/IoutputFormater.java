package InterfacesformatterEngine;

import Interfaces.Schedule;

import java.util.List;

public interface IoutputFormater {
    String scheduleToString (List<Schedule>  LS);
    String scheduleToString (Schedule  LS);
}
