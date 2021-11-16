package Implementations;

import java.time.DayOfWeek;
import java.time.LocalTime;

import Interfaces.Course;
import Interfaces.Room;
import Interfaces.Instructor;

public class IMSchedule implements Interfaces.Schedule {
    private Room Room;
    private LocalTime FromTime;
    private LocalTime ToTime;
    private Instructor Instructor;
    private Interfaces.Course Course;
    private java.time.DayOfWeek day;

    IMSchedule(Room room, LocalTime fromTime, LocalTime toTime, Instructor instructor, IMCourse course) {
        this.Room = room;
        this.FromTime = fromTime;
        this.ToTime = toTime;
        this.Instructor = instructor;
        this.Course = course;
    }

    public String getRoom() {
        return this.Room.getBuilding() + " " + this.Room.getRoomNumber();
    }

    public LocalTime getFromTime() {
        return this.FromTime;
    }

    public LocalTime getToTime() {
        return this.ToTime;
    }

    public String getInstructor() {
        return this.Instructor.getFirstName() + " " + this.Instructor.getLastName();
    }

    public String getCourse() {
        return this.Course.getTitle();
    }

    @Override
    public DayOfWeek getDay() {
        return this.day;
    }
}
