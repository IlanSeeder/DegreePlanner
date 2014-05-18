package fragments.content;

import repositories.GraphicPlannerRepository;

import com.example.degree_planner.R;

import data.viewModels.GraphicPlannerViewModel;
import fragments.alertDialogs.CourseAlertDialogFragment;
import fragments.alertDialogs.addCoursesFragment;
import android.app.DialogFragment;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ExpandableListView;
import android.widget.ExpandableListView.OnChildClickListener;
import android.widget.Toast;
import data.models.UserCourse;

public class GraphicPlannerFragment extends Fragment {
	public static final String ARG_MENU_NUMBER = "menu_number";
	public static final String ADD_COURSE = "Add Course";

	public UserCourse AddCourseTest = new UserCourse();

	// ViewModel
	public GraphicPlannerViewModel model = new GraphicPlannerViewModel();

	public GraphicPlannerFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Fragment Preparation
		View rootView = inflater.inflate(R.layout.main_fragment, container,
				false);
		int i = getArguments().getInt(ARG_MENU_NUMBER);
		String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
		getActivity().setTitle(menuTitle);

		// Prepare Data for the View
		model.expListView = (ExpandableListView) rootView
				.findViewById(R.id.lvExp);

		GraphicPlannerRepository.getUserPlannerData(model, getActivity()
				.getBaseContext());

		// On Item Click
		model.expListView.setOnChildClickListener(new OnChildClickListener() {
			@Override
			public boolean onChildClick(ExpandableListView parent, View view,
					final int groupPosition, int childPosition, long id) {

				UserCourse currentItem = GraphicPlannerRepository.GetItem(
						model, groupPosition, childPosition);

				// Add Course Clicked
				if (currentItem.Id == 0) {
					showAddCouses(groupPosition, childPosition);
//					// Choose Course
//					UserCourse courseToAdd = AddCourseTest;
//					courseToAdd.CourseName = "Added Course - 123456";
//					courseToAdd.Id = 5;
//					courseToAdd.Status = 2;
//					courseToAdd.Grade = -1;
//					courseToAdd.SemesterId = GraphicPlannerRepository
//							.GetSemester(model, groupPosition).Id;
//					// Add
//					boolean result = GraphicPlannerRepository.AddItem(model,
//							groupPosition, courseToAdd);
//
//					// Course Exists in current Semester
//					if (result) {
//						Toast.makeText(
//								getActivity().getBaseContext(),
//								"The course " + courseToAdd.CourseName + " has been added",
//								Toast.LENGTH_LONG).show();
//					} else {
//						Toast.makeText(
//								getActivity().getBaseContext(),
//								"The course " + courseToAdd.CourseName
//										+ " is already in semester",
//								Toast.LENGTH_LONG).show();
//					}
				}

				// Open Course Window
				else {
					showDialog(groupPosition, childPosition,
							currentItem.CourseName, currentItem.Id,
							currentItem.Grade, currentItem.Status);
				}
				return false;
			}
		});

		return rootView;
	}

	// Open Course Pop-up
	void showDialog(int groupPosition, int childPosition, CharSequence title,
			int itemId, int itemGrade, int itemStatus) {
		DialogFragment newFragment = CourseAlertDialogFragment.newInstance(
				groupPosition, childPosition, title, itemId, itemGrade,
				itemStatus);
		newFragment.show(getFragmentManager(), "dialog");
	}
	
	// Open AddCourses Pop Up
	void showAddCouses(int groupPosition, int childPosition) {
		DialogFragment newFragment = addCoursesFragment.newInstance(groupPosition, childPosition);
		newFragment.show(getFragmentManager(), "addCoursesPopUp");
	}
}