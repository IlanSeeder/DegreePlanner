package activities;

import com.example.degree_planner.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;

public class Login extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_port);
		getActionBar().setBackgroundDrawable(new ColorDrawable(Color.parseColor("#000066")));	
    }
    
    public void openDegreeSelectWindow(View view){
    	Intent intent = new Intent(this,MainActivity.class);
    	startActivity(intent);
    	
    }    
}
