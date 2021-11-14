package Implementations;

import java.time.LocalTime;

public class IMSchedule implements Interfaces.Schedule {
    private String Room;
    private LocalTime FromTime;
    private LocalTime ToTime;
    private String Instructor;
    private String Course;

    IMSchedule(String room, LocalTime fromTime, LocalTime toTime, String instructor, String course) {
        this.Room = room;
        this.FromTime = fromTime;
        this.ToTime = toTime;
        this.Instructor = instructor;
        this.Course = course;
    }

    public String getRoom() {
        return this.Room;
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
