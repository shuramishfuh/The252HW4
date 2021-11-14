 package Implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeMap;

import Interfaces.Course;
import Interfaces.DayOfWeek;
import Interfaces.Instructor;
import Interfaces.Room;
import Interfaces.Schedule;

public class IMCourSeera implements Interfaces.CourSeera {
    private TreeMap<Room, List<Schedule>> roomSched;

    IMCourSeera(List<Course> courses) {
        List<String> roomsCreated = new ArrayList<String>();
        roomSched = new TreeMap<Room, List<Schedule>>();
        for (Course courese : courses) {

        }
        for (Course c : courses) {
            if (c.getBldg().length() > 2 & c.getRoom().length() > 2) { // remove all none rooms
                Room room = new IMRoom(c.getBldg(), c.getRoom());
                Schedule s = new IMSchedule(
                       (c.getBldg() +" "+ c.getRoom()),
                        c.getBegin_time(),
                        c.getEnd_time(),
                        (c.getInstructor_first()+" "+ c.getInstructor_last()),
                        c.getTitle()
                );
//               if (roomSched.isEmpty()){
//                   roomSched.put(room,s);
//               }
                System.out.println(s.getRoom() + " " + s.getFromTime() + " " + s.getToTime() + " " + s.getInstructor() + " " + s.getCourse());
//				System.out.println(roomsCreated);
            }


//
//
//			if(roomSched.isEmpty()||!roomsCreated.contains(c.getBldg()+" "+ c.getRoom())) {
//				List<Schedule> lista = Arrays.asList(s);
//				roomSched.put(null, lista);
//			}else {
//				roomSched.get(r).add(s);
//			}
        }
    }

    @Override
    public TreeMap<Room, List<Schedule>> roomSchedule() {
        // TODO Auto-generated method stub
        return this.roomSched;
    }

    @Override
    public List<Schedule> roomSchedule(Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> roomSchedule(Room room, LocalDate date) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> roomSchedule(Room room, DayOfWeek day) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Schedule whoWasThereLast(Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Schedule whoIsThereNow(Room room) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> profSchedule(Instructor instructor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Schedule whereIsProf(Instructor instructor) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Schedule> whereWillProfBe(Instructor instructor) {
        // TODO Auto-generated method stub
        return null;
    }

}
