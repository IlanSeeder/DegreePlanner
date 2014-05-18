package fragments.content; 
  
import android.app.Fragment; 
import android.os.Bundle; 
import android.view.LayoutInflater; 
import android.view.View; 
import android.view.ViewGroup; 
import android.widget.ArrayAdapter; 
import android.widget.ListView; 
import com.example.degree_planner.R; 
  
public class ChangeDegreeFragment extends Fragment { 
    public static final String ARG_MENU_NUMBER = "menu_number"; 
    
    private ListView listView; 
  
    public ChangeDegreeFragment() { 
        // Empty constructor required for fragment subclasses 
    } 
  
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, 
            Bundle savedInstanceState) { 
          
        // Fragment Preparation 
        View rootView = inflater.inflate(R.layout.change_degree_fragment, 
                container, false); 
        int i = getArguments().getInt(ARG_MENU_NUMBER); 
        String menuTitle = getResources().getStringArray(R.array.menu_list)[i]; 
        getActivity().setTitle(menuTitle); 
          
        //list view setup 
        listView = (ListView) rootView.findViewById(R.id.list_courses); 
        ArrayAdapter<CharSequence> adapter = ArrayAdapter 
                .createFromResource(getActivity().getBaseContext(), 
                        R.array.computer_science_degree_types_array, 
                        android.R.layout.simple_list_item_single_choice);
        listView.setAdapter(adapter); 
  
        return rootView; 
    } 
} 