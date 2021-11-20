package Implementations;

import Interfaces.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class IMCourSeera implements Interfaces.CourSeera, Comparator<Schedule> {
    private TreeMap<Room, List<Schedule>> roomSchedule;


    /*
     * @param rooms -> list of all courses
     * @param room -> single room
     *
     * checks of room exist in rooms
     * */
    private boolean sameRoom(List<Room> rooms, Room room) {
        for (Room r : rooms)
            if (r.getBuilding().equals(room.getBuilding()) && r.getRoomNumber().equals(room.getRoomNumber()))
                return true;
        return false;
    }

    IMCourSeera(List<Course> courses) {

        List<Room> roomsCreated = new ArrayList<Room>();

        roomSchedule = new TreeMap<Room, List<Schedule>>();

        for (Course c : courses) {
            if (c.getBldg().length() < 2 || c.getRoom().length() < 2)
                continue;

            Room room = new IMRoom(c.getBldg(), c.getRoom());

            Instructor instructor = new IMInstructor(c.getInstructor_first(), c.getInstructor_last());

            if (!sameRoom(roomsCreated, room)) {
                List<Schedule> sh = new ArrayList<Schedule>();
                roomsCreated.add(room);
                sh.add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, (IMCourse) c));
                roomSchedule.put(room, sh);
            } else {
                roomSchedule.get(room)
                        .add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, (IMCourse) c));
            }
        }
        roomSchedule.put(new IMRoom("Fadi","420"),new ArrayList<>());
        for (List<Schedule> sh : roomSchedule.values()) {
            Collections.sort(sh, new ScheduleComparator());
        }
    }


    @Override
    public TreeMap<Room, List<Schedule>> roomSchedule() {
        return this.roomSchedule;
    }

    @Override
    /*
     * @param room -> room to find schedule
     *
     * returns schedule of a particular room
     * */
    public List<Schedule> roomSchedule(Room room) {
        try {
            return roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getRoom().equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public List<Schedule> roomSchedule(Room room, LocalDate date) {
        return roomSchedule().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()).stream()
                .filter(u -> u.getRoom().equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                .filter(u -> u.getDay().contains(date.getDayOfWeek()))
                .collect(Collectors.toList());

    }


    @Override
    public List<Schedule> roomSchedule(Room room, DayOfWeek day) {
        return roomSchedule().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()).stream()
                .filter(u -> u.getRoom().equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                .filter(u -> u.getDay().contains(day))
                .collect(Collectors.toList());
    }

    @Override
    public Schedule whoWasThereLast(Room room) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime localDate = LocalDateTime.now();
        LocalTime time = LocalTime.parse(dtf.format(localDate));
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        java.time.DayOfWeek dayToMoveBackWards = dayOfWeek;
        Schedule schedule;
        List<Schedule> list;
        try {

            do {
                LocalTime finalTime = time;
                java.time.DayOfWeek finalDayToMoveBackWards = dayToMoveBackWards;
                list = roomSchedule().values().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList()).stream()
                        .filter(u -> u.getDay().contains(finalDayToMoveBackWards))
                        .filter(u -> u.getRoom().equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                        .filter(t -> t.getToTime().isBefore(finalTime))
                        .collect(Collectors.toList());

                if (list.size() != 0) break;
                dayToMoveBackWards = dayToMoveBackWards.plus(-1);
                time = LocalTime.parse("23:59");
            } while (!dayOfWeek.equals(dayToMoveBackWards));


            if (list.size() == 0) {
                System.out.printf("this room has no classes");
                return null;
            }
            schedule = list.get(list.size() - 1);
            return schedule;
        } catch (Exception e) {
            System.out.printf("this class does not exist ");
            return null;
        }
    }

    @Override
    public Schedule whoIsThereNow(Room room) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDate localDate = LocalDate.now();
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        LocalTime time = LocalTime.parse(dtf.format(localDate));
        try {
            return roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getDay().contains(dayOfWeek))
                    .filter(u -> u.getRoom().equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .filter(t -> t.getFromTime().isBefore(time) && t.getToTime().isAfter(time))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    /*
     * @Param instructor -> instructor to find schedule
     *
     * returns schedule of an instructor for a  week
     * */
    public List<Schedule> profSchedule(Instructor instructor) {
        return roomSchedule().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()).stream()
                .filter(u -> u.getInstructor().equalsIgnoreCase((instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                .collect(Collectors.toList());

    }

    @Override
    /*
     * @Param instructor -> instructor to find schedule
     *
     * returns schedule of an instructor for a particular time
     * */
    public Schedule whereIsProf(Instructor instructor) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
        LocalDateTime localDate = LocalDateTime.now();
        LocalTime time = LocalTime.parse(dtf.format(localDate));
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();

        try {
            return roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getDay().contains(dayOfWeek))
                    .filter(u -> u.getInstructor().equalsIgnoreCase((instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                    .filter(t -> t.getFromTime().isBefore(time) && t.getToTime().isAfter(time))
                    .collect(Collectors.toList()).get(0);
        } catch (Exception e) {
            return null;
        }


    }

    @Override
    /*
     * @Param instructor -> instructor to find schedule
     *
     * returns schedule of an instructor for a particular day
     * */
    public List<Schedule> whereWillProfBe(Instructor instructor) {
        LocalDate localDate = LocalDate.now();
        java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
        return roomSchedule().values().stream()
                .flatMap(List::stream)
                .collect(Collectors.toList()).stream()
                .filter(u -> u.getDay().contains(dayOfWeek))
                .filter(u -> u.getInstructor().equalsIgnoreCase((instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                .collect(Collectors.toList());
    }

    @Override
    public int compare(Schedule o1, Schedule o2) {
        return o1.getToTime().compareTo(o2.getToTime());
    }
}
