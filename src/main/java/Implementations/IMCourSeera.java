package Implementations;

import Interfaces.*;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class IMCourSeera implements Interfaces.CourSeera {// , Comparator<Schedule> {
    private TreeMap<Room, List<Schedule>> roomSchedule;

    /**
     * checks if room exists in rooms
     * 
     * @param rooms list of all courses
     * @param room single room
     *
     * @return a boolean
     */
    private boolean sameRoom(List<Room> rooms, Room room) {
        for (Room r : rooms)
            if (r.getBuilding().equals(room.getBuilding()) && r.getRoomNumber().equals(room.getRoomNumber()))
                return true;
        return false;
    }

    /**
     * Constructor that builds a TreeMap where its keys are rooms and values are lists of schedules
     * @param courses a list of all courses 
     */
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
        for (List<Schedule> sh : roomSchedule.values()) {
            Collections.sort(sh, new ScheduleComparator());
        }
    }

    @Override
    public TreeMap<Room, List<Schedule>> roomSchedule() {
        return this.roomSchedule;
    }

    
    /**
     * lists the schedule for a specific room
     * 
     * @param room  room to find schedule
     *
     * @returns A list of schedules of a particular room
     * 
     * @throws IllegalArgumentException If room is not found
     */
    @Override
    public List<Schedule> roomSchedule(Room room) {
        try {
            return roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getRoom()
                            .equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    /**
     * lists the schedule for a specific room for a specific date
     * 
     * @param room  room to find schedule
     * @param date Localdate to find schedule for a room on a specific date
     * @return A list of schedules of a particular room
     * 
     * @throws IllegalArgumentException If room is not found
     * @throws IllegalStateException    If room has no classes on a specific date
     */
    @Override
    public List<Schedule> roomSchedule(Room room, LocalDate date) {
        try {
            boolean isvalidroom = roomSchedule.containsKey(room);
            if (!isvalidroom)
                throw new IllegalArgumentException();
            List<Schedule> sch = roomSchedule().values().stream()
                    .flatMap(List::stream).collect(Collectors.toList()).stream()
                    .filter(u -> u.getRoom()
                            .equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .filter(u -> u.getDay().contains(date.getDayOfWeek()))
                    .collect(Collectors.toList());
            if (sch.isEmpty())
                throw new IllegalStateException();
            return sch;
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * lists the schedule for a specific room for a specific day of week
     * 
     * @param room  room to find schedule
     * @param day DayOfWeek to find schedule for a room on a specific day
     * @return A list of schedules of a particular room
     * 
     * @throws IllegalArgumentException If room is not found
     * @throws IllegalStateException    If room has no classes on a specific day
     */
    @Override
    public List<Schedule> roomSchedule(Room room, DayOfWeek day) {
        try {
            boolean isvalidroom = roomSchedule.containsKey(room);
            if (!isvalidroom)
                throw new IllegalArgumentException();
            List<Schedule> sch = roomSchedule().values().stream()
                    .flatMap(List::stream).collect(Collectors.toList()).stream()
                    .filter(u -> u.getRoom()
                            .equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .filter(u -> u.getDay().contains(day))
                    .collect(Collectors.toList());
            if (sch.isEmpty())
                throw new IllegalStateException();
            return sch;
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * lists the course and instructor name for the last time this room was occupied
     * 
     * @param room  room to find who was there last 
     * 
     * @return schedule of a particular room 
     * 
     * @throws IllegalArgumentException If room is not found
     */
    @Override
    public Schedule whoWasThereLast(Room room) {
        try {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localDate = LocalDateTime.now();
            LocalTime time = LocalTime.parse(dtf.format(localDate));
            java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            java.time.DayOfWeek dayToMoveBackWards = dayOfWeek;
            Schedule schedule;
            List<Schedule> list;

            do {
                LocalTime finalTime = time;
                java.time.DayOfWeek finalDayToMoveBackWards = dayToMoveBackWards;
                list = roomSchedule().values().stream()
                        .flatMap(List::stream)
                        .collect(Collectors.toList()).stream()
                        .filter(u -> u.getDay().contains(finalDayToMoveBackWards))
                        .filter(u -> u.getRoom()
                                .equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                        .filter(t -> t.getToTime().isBefore(finalTime))
                        .collect(Collectors.toList());

                if (list.size() != 0)
                    break;
                dayToMoveBackWards = dayToMoveBackWards.plus(-1);
                time = LocalTime.parse("23:59");
            } while (!dayOfWeek.equals(dayToMoveBackWards));

            schedule = list.get(list.size() - 1);
            return schedule;
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    /**
     * lists the course and instructor name currently occupying a specific room
     * 
     * @param room  room to find who is there now 
     * 
     * @return schedule of a particular room 
     * 
     * @throws IllegalArgumentException If room is not found
     * @throws IllegalStateException    If no one is in the room now
     */    
    @Override
    public Schedule whoIsThereNow(Room room) {
        try {
            boolean isvalidroom = roomSchedule.containsKey(room);
            if (!isvalidroom)
                throw new IllegalArgumentException();

            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localDate = LocalDateTime.now();
            java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            LocalTime time = LocalTime.parse(dtf.format(localDate));
            List<Schedule> sch = roomSchedule().values().stream().flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getDay().contains(dayOfWeek))
                    .filter(u -> u.getRoom()
                            .equalsIgnoreCase((room.getBuilding().trim() + " " + room.getRoomNumber().trim())))
                    .filter(t -> t.getFromTime().isBefore(time) && t.getToTime().isAfter(time))
                    .collect(Collectors.toList());
            if (sch.isEmpty())
                throw new IllegalStateException();
            return sch.get(0);

        } catch (IllegalStateException e) {
            throw new IllegalStateException();

        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    
    /**
     * lists the instructor's weekly schedule (day, time, room)
     * 
     * @param instructor instructor to find schedule
     *
     * @return A list of schedules of an instructor
     * 
     * @throws IllegalArguementException if instructor is not found
     */
    @Override
    public List<Schedule> profSchedule(Instructor instructor) {
        try {
            return roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getInstructor()
                            .equalsIgnoreCase(
                                    (instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }

    }

    
    /**
     * lists the room where a prof is currently teaching (if any)
     * 
     * @param instructor instructor to find 
     *
     * @return schedule of an instructor 
     * 
     * @throws IllegalArguementException if instructor is not found
     * @throws IllegalStateException     if instructor is not in any room currently
     */
    @Override
    public Schedule whereIsProf(Instructor instructor) {
        try {
            boolean isvalidprof = roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .anyMatch(u -> u.getInstructor()
                            .equalsIgnoreCase(instructor.getFirstName() + " " + instructor.getLastName()));
            if (!isvalidprof) {
                throw new IllegalArgumentException();
            }
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm");
            LocalDateTime localDate = LocalDateTime.now();
            LocalTime time = LocalTime.parse(dtf.format(localDate));
            java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();

            List<Schedule> sch = roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getDay().contains(dayOfWeek))
                    .filter(u -> u.getInstructor().equalsIgnoreCase(
                            (instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                    .filter(t -> t.getFromTime().isBefore(time) && t.getToTime().isAfter(time))
                    .collect(Collectors.toList());
            if (sch.isEmpty())
                throw new IllegalStateException();
            return sch.get(0);
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }

    
    /** 
     * lists the instructor's schedule for today
     * 
     * @param instructor instructor to find schedule
     *
     * @return a list of schedules of an instructor for a today
     * 
     * @throws IllegalArguementException if the instructor is not found
     * @throws IllegalStateException     if the instructor teaches no classes today
     */
    @Override
    public List<Schedule> whereWillProfBe(Instructor instructor) {
        try {
            boolean isvalidprof = roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .anyMatch(u -> u.getInstructor()
                            .equalsIgnoreCase(instructor.getFirstName() + " " + instructor.getLastName()));
            if (!isvalidprof) {
                throw new IllegalArgumentException();
            }
            LocalDate localDate = LocalDate.now();
            java.time.DayOfWeek dayOfWeek = localDate.getDayOfWeek();
            List<Schedule> sch = roomSchedule().values().stream()
                    .flatMap(List::stream)
                    .collect(Collectors.toList()).stream()
                    .filter(u -> u.getDay().contains(dayOfWeek))
                    .filter(u -> u.getInstructor()
                            .equalsIgnoreCase(
                                    (instructor.getFirstName().trim() + " " + instructor.getLastName().trim())))
                    .collect(Collectors.toList());
            if(sch.isEmpty())
                throw new IllegalStateException();
            return sch;
        } catch (IllegalStateException e) {
            throw new IllegalStateException();
        } catch (Exception e) {
            throw new IllegalArgumentException();
        }
    }
}
