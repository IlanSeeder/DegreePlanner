package fragments.content;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.degree_planner.R;


public class DetailsFragment extends Fragment {
	public static final String ARG_MENU_NUMBER = "menu_number";
	
	private ListView listView;
	
	public DetailsFragment() {
		// Empty constructor required for fragment subclasses
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		// Fragment Preparation
		View rootView = inflater.inflate(R.layout.details_fragment,
				container, false);
		int i = getArguments().getInt(ARG_MENU_NUMBER);
		String menuTitle = getResources().getStringArray(R.array.menu_list)[i];
		getActivity().setTitle(menuTitle);
		
//		//listview setup
		listView = (ListView) rootView.findViewById(R.id.personal_details);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter
				.createFromResource(getActivity().getBaseContext(),
						R.array.personal_details_list,
						android.R.layout.simple_list_item_1);
		
		listView.setAdapter(adapter);

		
		Toast.makeText(this.getActivity(), "AAAAAAAAAAAAA", Toast.LENGTH_LONG).show();
		
		
		
		
		return rootView;
	}
}
