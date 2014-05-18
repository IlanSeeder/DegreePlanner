package data.viewModels;

import java.util.HashMap;
import java.util.List;

import data.models.ExpandableListAdapter;
import data.models.Semester;
import data.models.UserCourse;
import android.widget.ExpandableListView;

public class GraphicPlannerViewModel {
	public ExpandableListAdapter listAdapter;
	public ExpandableListView expListView;
	public List<Semester> listDataHeader;
	public HashMap<Semester, List<UserCourse>> listDataChild;
}
