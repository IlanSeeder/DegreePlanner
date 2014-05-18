package fragments.alertDialogs;

import com.example.degree_planner.R;

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
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class CourseAlertDialogFragment extends DialogFragment {
	public static CourseAlertDialogFragment newInstance(int groupPosition,
			int childPosition, CharSequence title, int itemId, int itemGrade,
			int itemStatus) {
		CourseAlertDialogFragment frag = new CourseAlertDialogFragment();
		Bundle args = new Bundle();
		args.putCharSequence("dialogBoxTitle", title);
		args.putInt("groupPosition", groupPosition);
		args.putInt("childPosition", childPosition);
		args.putInt("itemId", itemId);
		args.putInt("itemGrade", itemGrade);
		args.putInt("itemStatus", itemStatus);
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
		final int itemGrade = getArguments().getInt("itemGrade");
		CharSequence status = getString(R.string.status) + " "
				+ SetStatusString((getArguments().getInt("itemStatus")));
		String gradeString = (itemGrade >= 0) ? getString(R.string.grade) + " "
				+ itemGrade : getString(R.string.grade);

		LayoutInflater layoutInflater = (LayoutInflater) getActivity()
				.getBaseContext().getSystemService(
						Context.LAYOUT_INFLATER_SERVICE);
		View view = layoutInflater.inflate(R.layout.course_dialog_fragment,
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
		
		final AlertDialog dialog = builder.create();

		TextView mTitle = (TextView) view.findViewById(R.id.title);
		mTitle.setText(title);
		TextView mGrade = (TextView) view.findViewById(R.id.grade);
		mGrade.setText(gradeString);
		TextView mStatus = (TextView) view.findViewById(R.id.status);
		mStatus.setText(status);

		Button moveButton = (Button) view.findViewById(R.id.move);
		moveButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onDialogNegativeClick(CourseAlertDialogFragment.this,
						groupPosition, childPosition, title, itemId);
				dialog.dismiss();
			}
		});

		Button deleteButton = (Button) view.findViewById(R.id.delete);
		deleteButton.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				mListener.onDialogPositiveClick(CourseAlertDialogFragment.this,
						groupPosition, childPosition, title, itemId);
				dialog.dismiss();
			}
		});
		return dialog;
	}

	// Listener to AlertDialog
	public interface NoticeDialogListener {
		public void onDialogPositiveClick(DialogFragment dialog,
				int groupPosition, int childPosition, CharSequence title,
				int itemId);

		public void onDialogNegativeClick(DialogFragment dialog,
				int groupPosition, int childPosition, CharSequence title,
				int itemId);
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

	private String SetStatusString(int statusId) {
		String status;
		switch (statusId) {
		case 0:
			status = getString(R.string.passed);
			break;
		case 1:
			status = getString(R.string.failed);
			break;
		case 2:
			status = getString(R.string.taking);
			break;
		case 3:
			status = getString(R.string.problematic);
			break;
		default:
			status = getString(R.string.none);
			break;
		}
		return status;
	}

}
