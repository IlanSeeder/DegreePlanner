package fragments.alertDialogs;

import java.util.ArrayList;

import repositories.GraphicPlannerRepository;

import com.example.degree_planner.R;

import data.models.Semester;
import activities.MainActivity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class MoveSemesterAlertDialogFragment extends DialogFragment {
	public static MoveSemesterAlertDialogFragment newInstance(
			int groupPosition, int childPosition, CharSequence title, int itemId) {
		MoveSemesterAlertDialogFragment frag = new MoveSemesterAlertDialogFragment();
		Bundle args = new Bundle();
		args.putCharSequence("dialogBoxTitle", title);
		args.putInt("groupPosition", groupPosition);
		args.putInt("childPosition", childPosition);
		args.putInt("itemId", itemId);
		frag.setArguments(args);
		return frag;
	}

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		final CharSequence title = getArguments().getCharSequence(
				"dialogBoxTitle");
		final int groupPosition = getArguments().getInt("groupPosition");
		final int childPosition = getArguments().getInt("childPosition");
		final int itemId = getArguments().getInt("itemId");
		final ArrayList<Semester> semesters = GraphicPlannerRepository
				.GetUserSemesters();

		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getBaseContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(
				R.layout.move_semester_dialog_fragment, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(getActivity(),
						R.style.AlertDialogCustom));

		TextView mTitle = (TextView) view.findViewById(R.id.title);
		mTitle.setText("Move " + title + " to semester:");

		final ListView listView = (ListView) view
				.findViewById(R.id.list_semesters);
		ArrayAdapter<String> adapter = new ArrayAdapter<String>(getActivity()
				.getBaseContext(),
				android.R.layout.simple_list_item_single_choice);
		for (int i = 0; i < semesters.size(); i++) {
			adapter.add(GetSemesterString(semesters.get(i).Season,
					semesters.get(i).Year));
		}
		listView.setAdapter(adapter);
		builder.setView(view)
				.setCancelable(false)
				.setNegativeButton("Cancal",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								dialog.cancel();
							}
						})
				.setPositiveButton("OK", new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int id) {
						int selectedItem = listView.getCheckedItemPosition();
						if (selectedItem >= 0) {
							((MainActivity) getActivity()).MoveSemester(
									groupPosition, childPosition, itemId,
									semesters.get((int) selectedItem).Id);
							dialog.dismiss();
						}
					}
				});

		final AlertDialog dialog = builder.create();
		return dialog;
	}

	private String GetSemesterString(int seasonId, int year) {
		String headerTitle;
		switch (seasonId) {
		case 0:
			headerTitle = getActivity().getBaseContext().getString(
					R.string.spring);
			break;
		case 1:
			headerTitle = getActivity().getBaseContext().getString(
					R.string.summer);
			break;
		default:
			headerTitle = getActivity().getBaseContext().getString(
					R.string.winter);
			break;
		}
		return headerTitle + " " + year;
	}
}
