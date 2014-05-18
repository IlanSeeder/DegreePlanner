package repositories;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.content.Context;
import data.models.ExpandableListAdapter;
import data.viewModels.GraphicPlannerViewModel;
import data.models.Semester;
import data.models.UserCourse;
import fragments.content.GraphicPlannerFragment;

public class GraphicPlannerRepository {

	// Get Data From DataBase
	public static void getUserPlannerData(GraphicPlannerViewModel model,
			Context context) {

		model.listDataHeader = new ArrayList<Semester>();
		model.listDataChild = new HashMap<Semester, List<UserCourse>>();

		model.listAdapter = new ExpandableListAdapter(context,
				model.listDataHeader, model.listDataChild);

		model.expListView.setAdapter(model.listAdapter);

		// Adding Header data
		Semester semester1 = new Semester();
		semester1.Id = 1;
		semester1.Year = 2013;
		semester1.Season = 2;
		semester1.UserId = 1;
		Semester semester2 = new Semester();
		semester2.Id = 2;
		semester2.Year = 2014;
		semester2.Season = 0;
		semester2.UserId = 1;
		Semester semester3 = new Semester();
		semester3.Id = 3;
		semester3.Year = 2014;
		semester3.Season = 1;
		semester3.UserId = 1;

		model.listDataHeader.add(semester1);
		model.listDataHeader.add(semester2);
		model.listDataHeader.add(semester3);

		// Adding child data
		List<UserCourse> courses_in_semester1 = new ArrayList<UserCourse>();
		UserCourse userCourse1 = new UserCourse();
		userCourse1.Id = 1;
		userCourse1.Status = 1;
		userCourse1.CourseName = "Program Language - 234331";
		userCourse1.Grade = 40;
		userCourse1.CourseId = 1;
		userCourse1.UserId = 1;
		userCourse1.SemesterId = 1;
		UserCourse userCourse2 = new UserCourse();
		userCourse2.Id = 2;
		userCourse2.Status = 0;
		userCourse2.CourseName = "Physics - 232513";
		userCourse2.Grade = 80;
		userCourse2.CourseId = 2;
		userCourse2.UserId = 1;
		userCourse2.SemesterId = 1;
		UserCourse userCourse3 = new UserCourse();
		userCourse3.Id = 3;
		userCourse3.Status = 3;
		userCourse3.CourseName = "Sport - 474513";
		userCourse3.Grade = 60;
		userCourse3.CourseId = 3;
		userCourse3.UserId = 1;
		userCourse3.SemesterId = 2;
		UserCourse userCourse4 = new UserCourse();
		userCourse4.Id = 4;
		userCourse4.Status = 4;
		userCourse4.CourseName = "Logic - 110513";
		userCourse4.Grade = -1;
		userCourse4.CourseId = 4;
		userCourse4.UserId = 1;
		userCourse4.SemesterId = 3;

		UserCourse addButton = new UserCourse();
		addButton.CourseName = GraphicPlannerFragment.ADD_COURSE;
		addButton.Id = 0;
		addButton.Status = 5;
		UserCourse addButton2 = new UserCourse();
		addButton2.CourseName = GraphicPlannerFragment.ADD_COURSE;
		addButton2.Id = 0;
		addButton2.Status = 5;
		UserCourse addButton3 = new UserCourse();
		addButton3.CourseName = GraphicPlannerFragment.ADD_COURSE;
		addButton3.Id = 0;
		addButton3.Status = 5;

		courses_in_semester1.add(userCourse1);
		courses_in_semester1.add(userCourse2);
		courses_in_semester1.add(addButton);

		List<UserCourse> courses_in_semester2 = new ArrayList<UserCourse>();
		courses_in_semester2.add(userCourse3);
		courses_in_semester2.add(addButton2);

		List<UserCourse> courses_in_semester3 = new ArrayList<UserCourse>();
		courses_in_semester3.add(userCourse4);
		courses_in_semester3.add(addButton3);

		model.listDataChild.put(model.listDataHeader.get(0),
				courses_in_semester1); // Header,
		model.listDataChild.put(model.listDataHeader.get(1),
				courses_in_semester2);// Child
		model.listDataChild.put(model.listDataHeader.get(2),
				courses_in_semester3);// data
	}

	// Get User's Semesters
	public static ArrayList<Semester> GetUserSemesters() {
		ArrayList<Semester> list = new ArrayList<Semester>();
		// Adding Header data
		Semester semester1 = new Semester();
		semester1.Id = 1;
		semester1.Year = 2013;
		semester1.Season = 2;
		semester1.UserId = 1;
		Semester semester2 = new Semester();
		semester2.Id = 2;
		semester2.Year = 2014;
		semester2.Season = 0;
		semester2.UserId = 1;
		Semester semester3 = new Semester();
		semester3.Id = 3;
		semester3.Year = 2014;
		semester3.Season = 1;
		semester3.UserId = 1;
		list.add(semester1);
		list.add(semester2);
		list.add(semester3);
		return list;
	}

	// Remove Item
	public static boolean RemoveItem(GraphicPlannerViewModel model,
			final int groupPosition, UserCourse item) {

		List<UserCourse> semesterCourses = model.listDataChild
				.get(model.listDataHeader.get(groupPosition));
		if (semesterCourses.contains(item)) {
			semesterCourses.remove(semesterCourses.indexOf(item));
			model.listAdapter.notifyDataSetChanged();
			return true;
		}
		return false;
	}

	// Add Item
	public static boolean AddItem(GraphicPlannerViewModel model,
			final int groupPosition, UserCourse item) {

		List<UserCourse> semesterCourses = model.listDataChild
				.get(model.listDataHeader.get(groupPosition));

		// Adding Course
		if (!semesterCourses.contains(item)) {
			semesterCourses.add(semesterCourses.size() - 1, item);
			model.listAdapter.notifyDataSetChanged();
			return true;
		}
		return false;
	}

	// Get Item
	public static UserCourse GetItem(GraphicPlannerViewModel model,
			final int groupPosition, int childPosition) {
		return model.listDataChild.get(model.listDataHeader.get(groupPosition))
				.get(childPosition);
	}

	public static Semester GetSemester(GraphicPlannerViewModel model,
			final int groupPosition) {
		return model.listDataHeader.get(groupPosition);
	}

	public static boolean MoveItemSemester(GraphicPlannerViewModel model,
			int groupPosition, int childPosition, UserCourse itemToMove,
			int semesterId) {

		int semesterGroupPosition = groupPosition;
		for (int i = 0; i < model.listDataHeader.size(); i++) {
			if (model.listDataHeader.get(i).Id == semesterId) {
				semesterGroupPosition = i;
			}
		}

		List<UserCourse> semesterListToAdd = model.listDataChild
				.get(model.listDataHeader.get(semesterGroupPosition));

		// Adding Course
		if (!semesterListToAdd.contains(itemToMove)) {
			semesterListToAdd.add(semesterListToAdd.size() - 1, itemToMove);
		} else {
			return false;
		}
		List<UserCourse> semesterListToRemove = model.listDataChild
				.get(model.listDataHeader.get(groupPosition));
		if (semesterListToRemove.contains(itemToMove)) {
			semesterListToRemove.remove(semesterListToRemove
					.indexOf(itemToMove));
		} else {
			return false;
		}
		model.listAdapter.notifyDataSetChanged();
		return true;
	}

	public static boolean AddSemester(GraphicPlannerViewModel model, int year,
			int semesterId) {
		Semester semester = new Semester();
		semester.Id = 4;
		semester.Year = year;
		semester.Season = semesterId;
		semester.UserId = 1;
		
		UserCourse addButton = new UserCourse();
		addButton.CourseName = GraphicPlannerFragment.ADD_COURSE;
		addButton.Id = 0;
		addButton.Status = 5;
		
		model.listDataHeader.add(semester);
		List<UserCourse> newSemesterCoursesList = new ArrayList<UserCourse>();
		newSemesterCoursesList.add(addButton);		
		
		model.listDataChild.put(model.listDataHeader.get(model.listDataHeader.size()-1),
				newSemesterCoursesList);
		model.listAdapter.notifyDataSetChanged();
		return true;
	}
}
