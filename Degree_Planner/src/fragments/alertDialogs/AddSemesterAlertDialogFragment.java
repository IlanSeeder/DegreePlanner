package fragments.alertDialogs;

import com.example.degree_planner.R;

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
import android.widget.Spinner;
import android.widget.TextView;

public class AddSemesterAlertDialogFragment extends DialogFragment {
	public static AddSemesterAlertDialogFragment newInstance() {
		AddSemesterAlertDialogFragment frag = new AddSemesterAlertDialogFragment();
		Bundle args = new Bundle();
		frag.setArguments(args);
		return frag;
	}

	private Spinner spinnerYear;
	private Spinner spinnerSemester;

	@Override
	public Dialog onCreateDialog(Bundle savedInstanceState) {
		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getBaseContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(
				R.layout.add_semester_dialog_fragment, null);

		AlertDialog.Builder builder = new AlertDialog.Builder(
				new ContextThemeWrapper(getActivity(),
						R.style.AlertDialogCustom));

		TextView mTitle = (TextView) view.findViewById(R.id.title);
		mTitle.setText("Add Semester");

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
						((MainActivity) getActivity()).AddSemesterToUser(
								Integer.parseInt((String) spinnerYear
										.getSelectedItem()), spinnerSemester
										.getSelectedItemPosition());
						dialog.dismiss();
					}
				});

		// SpinnerYear Setup
		spinnerYear = (Spinner) view.findViewById(R.id.spinnerYear);
		ArrayAdapter<CharSequence> adapterYear = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.yearOptions,
						android.R.layout.simple_spinner_item);
		adapterYear
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerYear.setAdapter(adapterYear);

		// SpinnerSemester Setup
		spinnerSemester = (Spinner) view.findViewById(R.id.spinnerSemester);
		ArrayAdapter<CharSequence> adapterSemester = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.semesterOptions,
						android.R.layout.simple_spinner_item);
		adapterSemester
				.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinnerSemester.setAdapter(adapterSemester);

		final AlertDialog dialog = builder.create();
		return dialog;
	}

}
