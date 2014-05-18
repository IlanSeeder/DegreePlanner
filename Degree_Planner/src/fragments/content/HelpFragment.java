package fragments.content;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.degree_planner.R;

public class HelpFragment extends Fragment {
	public static final String ARG_MENU_NUMBER = "menu_number";

	public HelpFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Fragment Preparation// Fragment Preparation
		View rootView = inflater.inflate(R.layout.help_fragment, container,
				false);
		int i = getArguments().getInt(ARG_MENU_NUMBER);
		String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
		getActivity().setTitle(menuTitle);
		
		return rootView;
	}
}