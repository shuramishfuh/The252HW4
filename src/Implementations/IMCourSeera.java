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
	private TreeMap<Room, List<Schedule>> roomSchedule;

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
				sh.add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, c.getTitle()));
				roomSchedule.put(room, sh);
			} else {
				roomSchedule.get(room)
						.add(new IMSchedule(room, c.getBegin_time(), c.getEnd_time(), instructor, c.getTitle()));
			}
		}
	}

	@Override
	public TreeMap<Room, List<Schedule>> roomSchedule() {
		// TODO Auto-generated method stub
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
	public List<Schedule> whereWillProfBe(Instructor instructor) {
		// TODO Auto-generated method stub
		return null;
	}
}
