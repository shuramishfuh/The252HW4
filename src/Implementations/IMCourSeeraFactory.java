package Implementations;

import java.util.List;

import Interfaces.CourSeera;
import Interfaces.Course;

public class IMCourSeeraFactory implements Interfaces.CourSeeraFactory{

	@Override
	public CourSeera createInstance(List<Course> courses) { 
		return new IMCourSeera(courses);
	}
}
