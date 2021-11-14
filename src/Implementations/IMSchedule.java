package Implementations;

import java.time.LocalTime;

public class IMSchedule implements Interfaces.Schedule{
	private IMRoom Room;
	private LocalTime FromTime;
	private LocalTime ToTime;
	private IMInstructor Instructor;
	private String Course;
	IMSchedule(IMRoom room, LocalTime fromTime, LocalTime toTime, IMInstructor instructor, String course) {
		this.Room = room;
		this.FromTime = fromTime;
		this.ToTime = toTime;
		this.Instructor = instructor;
		this.Course = course;
	}
	public String getRoom() {
		return this.Room.getBuilding()+ " " + this.Room.getRoomNumber();
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
