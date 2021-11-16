 package Implementations;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import Interfaces.Course;
import Interfaces.DayOfWeek;
import Interfaces.Instructor;
import Interfaces.Room;
import Interfaces.Schedule;

public class IMCourSeera implements Interfaces.CourSeera {
	private TreeMap<Room, List<Schedule>> roomSchedule;


	/*
	* @param rooms -> list of all courses
	* @param room -> single room
	*
	* checks of room exist in rooms
	* */
	private boolean sameRoom(List<Room> rooms, Room room) {
		for (Room r : rooms)
		if(r.getBuilding().equals(room.getBuilding()) && r.getRoomNumber().equals(room.getRoomNumber()))
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

			if(!sameRoom(roomsCreated, room)) { 
				List<Schedule> sh = new ArrayList<Schedule>();
				roomsCreated.add(room);
				sh.add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, (IMCourse) c));
				roomSchedule.put(room, sh);
			} else {
				roomSchedule.get(room)
						.add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, (IMCourse) c));
			}
		}
	}

	@Override
	public TreeMap<Room, List<Schedule>> roomSchedule() {
		return this.roomSchedule;
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
				.filter(u->u.getDay().contains(dayOfWeek))
				.filter(u ->u.getInstructor().equalsIgnoreCase((instructor.getFirstName().trim()+ " " +instructor.getLastName().trim())))
				.collect(Collectors.toList());

	}
}
