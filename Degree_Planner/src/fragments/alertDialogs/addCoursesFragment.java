package fragments.alertDialogs;

import com.example.degree_planner.R;

import fragments.alertDialogs.CourseAlertDialogFragment.NoticeDialogListener;
import activities.MainActivity;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class addCoursesFragment extends DialogFragment {
	
	private View view;
    private boolean isHidden = true;
	
	public static addCoursesFragment newInstance(int groupPosition, int childPosition) {
		addCoursesFragment frag = new addCoursesFragment();
		Bundle args = new Bundle();
		args.putCharSequence("dialogBoxTitle", "Add Courses");
		frag.setArguments(args);
		return frag;
	}
		
	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getBaseContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
		view = layoutInflater.inflate(R.layout.add_courses_fragment,
				null);
		
		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(getActivity(),
						R.style.AlertDialogCustom));
		
		builder.setView(view).setCancelable(false)
		.setNegativeButton("OK", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.cancel();
			}
		});
		Button searchOptions = (Button) view.findViewById(R.id.search_options_button);
		searchOptions.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {

				toggle_course_search_options();
			}
		});
		hide_search_course_options();
		
		builder.setView(view)
		.setCancelable(false)
		.setNegativeButton("Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						dialog.cancel();
					}
				})
		.setPositiveButton("Done", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
				dialog.dismiss();
			}
		})
		.setNeutralButton("Show Added", new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int id) {
			}
		});
		
		AlertDialog dialog = builder.create();
		dialog.show();
		
		dialog.getButton(AlertDialog.BUTTON_NEUTRAL).setOnClickListener(new View.OnClickListener()
	      {            
	          @Override
	          public void onClick(View v)
	          {
	              Boolean wantToCloseDialog = false;
	              Toast.makeText(getActivity(), "Not Yet", Toast.LENGTH_LONG).show();
	              if(wantToCloseDialog)
	                  dismiss();
	          }
	      });
		
		TextView mTitle = (TextView) view.findViewById(R.id.title);
		mTitle.setText("Add Courses");
		
		return dialog;
	}
	
	
	public void toggle_course_search_options()
	{
		if (isHidden == true)
		{
			show_search_course_options();
		}
		else
		{
			hide_search_course_options();
		}
	}
	
	NoticeDialogListener mListener;
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		// Verify that the host activity implements the callback interface
		try {
			// Instantiate the NoticeDialogListener so we can send events to the
			// host
			mListener = (NoticeDialogListener) activity;
		} catch (ClassCastException e) {
			// The activity doesn't implement the interface, throw exception
			throw new ClassCastException(activity.toString()
					+ "must implement NoticeDialogListener");
		}
	}
	
	private void hide_search_course_options(){
		//get all options to hide
		CheckBox listA = (CheckBox) view.findViewById(R.id.list_a);
		CheckBox listB = (CheckBox) view.findViewById(R.id.list_b);
		CheckBox obligatory = (CheckBox) view.findViewById(R.id.obligatory);
		CheckBox freeChoice = (CheckBox) view.findViewById(R.id.free_choice);
		Spinner faculties = (Spinner) view.findViewById(R.id.search_faculties);
		//hide the options
		listA.setVisibility(View.GONE);
		listB.setVisibility(View.GONE);
		obligatory.setVisibility(View.GONE);
		freeChoice.setVisibility(View.GONE);
		faculties.setVisibility(View.GONE);
		
		//set button text to "tap for search options"
		Button optionsButton = (Button) view.findViewById(R.id.search_options_button);
		optionsButton.setText("Tap to show search options");
		isHidden = true;
	}
	
	private void show_search_course_options(){
		//get all options to hide
		CheckBox listA = (CheckBox) view.findViewById(R.id.list_a);
		CheckBox listB = (CheckBox) view.findViewById(R.id.list_b);
		CheckBox obligatory = (CheckBox) view.findViewById(R.id.obligatory);
		CheckBox freeChoice = (CheckBox) view.findViewById(R.id.free_choice);
		Spinner faculties = (Spinner) view.findViewById(R.id.search_faculties);
		//hide the options
		listA.setVisibility(View.VISIBLE);
		listB.setVisibility(View.VISIBLE);
		obligatory.setVisibility(View.VISIBLE);
		freeChoice.setVisibility(View.VISIBLE);
		faculties.setVisibility(View.VISIBLE);
		
		//set button text to "tap to hide search options"
		Button optionsButton = (Button) view.findViewById(R.id.search_options_button);
		optionsButton.setText("Tap to hide search options");	
		isHidden = false;
	}

}
