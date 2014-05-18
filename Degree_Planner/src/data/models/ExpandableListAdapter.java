package data.models;

import java.util.HashMap;
import java.util.List;

import com.example.degree_planner.R;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.Button;
import android.widget.TextView;

public class ExpandableListAdapter extends BaseExpandableListAdapter {
	private Context _context;
	private List<Semester> _listDataHeader; // header titles
	// child data in format of header title, child title
	private HashMap<Semester, List<UserCourse>> _listDataChild;

	public ExpandableListAdapter(Context context,
			List<Semester> listDataHeader,
			HashMap<Semester, List<UserCourse>> listDataChild) {
		this._context = context;
		this._listDataHeader = listDataHeader;
		this._listDataChild = listDataChild;
	}

	@Override
	public Object getChild(int groupPosition, int childPosititon) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.get(childPosititon);
	}

	@Override
	public long getChildId(int groupPosition, int childPosition) {
		UserCourse userCourse = (UserCourse) getChild(groupPosition,
				childPosition);
		return userCourse.Id;
	}

	@Override
	public View getChildView(int groupPosition, final int childPosition,
			boolean isLastChild, View convertView, ViewGroup parent) {

		UserCourse userCourse = (UserCourse) getChild(groupPosition,
				childPosition);

		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater
					.inflate(R.layout.list_course_item, null);
		}

		Button txtListChild = (Button) convertView
				.findViewById(R.id.lblListItem);

		switch (userCourse.Status) {
		case 0: // Passed Course
			txtListChild.setBackground(_context.getResources().getDrawable(
					R.drawable.button_blue_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.passed, 0);
			txtListChild.setTextColor(Color.parseColor("#FFFFFF"));
			break;
		case 1: // Failed
			txtListChild.setBackground(_context.getResources().getDrawable(
					R.drawable.button_blue_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.failed, 0);
			txtListChild.setTextColor(Color.parseColor("#FFFFFF"));
			break;
		case 2: // Currently Taking
			txtListChild.setBackground(_context.getResources().getDrawable(
					R.drawable.button_blue_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.taking_now, 0);
			txtListChild.setTextColor(Color.parseColor("#FFFFFF"));
			break;
		case 3: // Problematic
			txtListChild.setBackground(_context.getResources().getDrawable(
					R.drawable.button_blue_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0, R.drawable.warning, 0);
			txtListChild.setTextColor(Color.parseColor("#FFFFFF"));
			break;
		case 4: // Future Course
			txtListChild.setBackground(_context.getResources().getDrawable(
					R.drawable.button_blue_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0,  R.drawable.taking_now, 0);
			txtListChild.setTextColor(Color.parseColor("#FFFFFF"));
			break;
		default:// Add Button
			txtListChild.setBackground(_context.getResources().getDrawable(R.drawable.button_grey_selector));
			txtListChild.setCompoundDrawablesWithIntrinsicBounds(0, 0, 0, 0);
			txtListChild.setTextColor(Color.parseColor("#000000"));
			break;
		}
		txtListChild.setText(userCourse.CourseName);
		return convertView;
	}

	@Override
	public int getChildrenCount(int groupPosition) {
		return this._listDataChild.get(this._listDataHeader.get(groupPosition))
				.size();
	}

	@Override
	public Object getGroup(int groupPosition) {
		return this._listDataHeader.get(groupPosition);
	}

	@Override
	public int getGroupCount() {
		return this._listDataHeader.size();
	}

	@Override
	public long getGroupId(int groupPosition) {
		Semester semester = (Semester) getGroup(groupPosition);
		return semester.Id;
	}

	@Override
	public View getGroupView(int groupPosition, boolean isExpanded,
			View convertView, ViewGroup parent) {
		Semester semester = (Semester) getGroup(groupPosition);
		if (convertView == null) {
			LayoutInflater infalInflater = (LayoutInflater) this._context
					.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			convertView = infalInflater.inflate(R.layout.list_group, null);
		}

		TextView lblListHeader = (TextView) convertView
				.findViewById(R.id.lblListHeader);
		lblListHeader.setTypeface(null, Typeface.BOLD);

		String headerTitle;
		switch (semester.Season) {
		case 0:
			headerTitle = _context.getString(R.string.spring);
			break;
		case 1:
			headerTitle = _context.getString(R.string.summer);
			break;
		default:
			headerTitle = _context.getString(R.string.winter);
			break;
		}
		String title = headerTitle + " " + semester.Year;
		lblListHeader.setText(title);

		return convertView;
	}

	@Override
	public boolean hasStableIds() {
		return false;
	}

	@Override
	public boolean isChildSelectable(int groupPosition, int childPosition) {
		return true;
	}
}
