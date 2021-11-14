package Implementations;

import java.time.LocalTime;
import Interfaces.Room;
import Interfaces.Instructor;

public class IMSchedule implements Interfaces.Schedule {
    private Room Room;
    private LocalTime FromTime;
    private LocalTime ToTime;
    private Instructor Instructor;
    private String Course;

    IMSchedule(Room room, LocalTime fromTime, LocalTime toTime, Instructor instructor, String course) {
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
        return this.Course;
    }
}
