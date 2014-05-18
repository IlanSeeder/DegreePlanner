package activities;

import repositories.GraphicPlannerRepository;

import com.example.degree_planner.R;

import data.models.UserCourse;
import fragments.alertDialogs.AddSemesterAlertDialogFragment;
import fragments.alertDialogs.CourseAlertDialogFragment;
import fragments.alertDialogs.MoveSemesterAlertDialogFragment;
import fragments.content.ChangeDegreeFragment;
import fragments.content.DetailsFragment;
import fragments.content.GraphicPlannerFragment;
import fragments.content.HelpFragment;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends Activity implements
		CourseAlertDialogFragment.NoticeDialogListener {

	private DrawerLayout mDrawerLayout;
	private ListView mDrawerList;
	private ActionBarDrawerToggle mDrawerToggle;

	private CharSequence mDrawerTitle;
	private CharSequence mTitle;
	private String[] menuTitles;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		mTitle = mDrawerTitle = getTitle();
		menuTitles = getResources().getStringArray(R.array.menu_list);
		mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
		mDrawerList = (ListView) findViewById(R.id.left_drawer);

		// set a custom shadow that overlays the main content when the drawer
		// opens
		mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow,
				GravityCompat.START);
		// set up the drawer's list view with items and click listener
		mDrawerList.setAdapter(new ArrayAdapter<String>(this,
				R.layout.drawer_list_item, menuTitles));
		mDrawerList.setOnItemClickListener(new DrawerItemClickListener());

		// enable ActionBar app icon to behave as action to toggle nav drawer
		getActionBar().setBackgroundDrawable(
				new ColorDrawable(Color.parseColor("#000066")));
		getActionBar().setDisplayHomeAsUpEnabled(true);
		getActionBar().setHomeButtonEnabled(true);

		// ActionBarDrawerToggle ties together the the proper interactions
		// between the sliding drawer and the action bar app icon
		mDrawerToggle = new ActionBarDrawerToggle(this, /* host Activity */
		mDrawerLayout, /* DrawerLayout object */
		R.drawable.ic_drawer, /* nav drawer image to replace 'Up' caret */
		R.string.drawer_open, /* "open drawer" description for accessibility */
		R.string.drawer_close /* "close drawer" description for accessibility */
		) {
			public void onDrawerClosed(View view) {
				getActionBar().setTitle(" " + mTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}

			public void onDrawerOpened(View drawerView) {
				getActionBar().setTitle(" " + mDrawerTitle);
				invalidateOptionsMenu(); // creates call to
											// onPrepareOptionsMenu()
			}
		};
		mDrawerLayout.setDrawerListener(mDrawerToggle);

		if (savedInstanceState == null) {
			selectItem(2); // TODO: after first time degree chosen should
							// replace to - 0
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return super.onCreateOptionsMenu(menu);
	}

	/* Called whenever we call invalidateOptionsMenu() */
	@Override
	public boolean onPrepareOptionsMenu(Menu menu) {
		// If the nav drawer is open, hide action items related to the content
		// view
		// boolean drawerOpen = mDrawerLayout.isDrawerOpen(mDrawerList);
		return super.onPrepareOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// The action bar home/up action should open or close the drawer.
		// ActionBarDrawerToggle will take care of this.
		if (mDrawerToggle.onOptionsItemSelected(item)) {
			return true;
		}
		// Handle action buttons
		switch (item.getItemId()) {
		default:
			return super.onOptionsItemSelected(item);
		}
	}

	/* The click listner for ListView in the navigation drawer */
	private class DrawerItemClickListener implements
			ListView.OnItemClickListener {
		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			selectItem(position);
		}
	}

	private void selectItem(int position) {
		// update the main content by replacing fragments
		Fragment fragment;
		switch (position) {
		case 0:
			fragment = new GraphicPlannerFragment();
			break;
		case 1:
			fragment = new DetailsFragment();
			break;
		case 2:
			fragment = new ChangeDegreeFragment();
			break;
		case 3:
			fragment = new HelpFragment();
			break;
		case 4:
			Intent intent = new Intent(this, Login.class);
			startActivity(intent);
			return;
		default:
			fragment = new GraphicPlannerFragment();
		}

		Bundle args = new Bundle();
		args.putInt(GraphicPlannerFragment.ARG_MENU_NUMBER, position);
		fragment.setArguments(args);

		FragmentManager fragmentManager = getFragmentManager();
		fragmentManager.beginTransaction()
				.replace(R.id.content_frame, fragment).commit();

		// update selected item and title, then close the drawer
		mDrawerList.setItemChecked(position, true);
		setTitle(" " + menuTitles[position]);
		mDrawerLayout.closeDrawer(mDrawerList);
	}

	@Override
	public void setTitle(CharSequence title) {
		mTitle = title;
		getActionBar().setTitle(" " + mTitle);
	}

	@Override
	protected void onPostCreate(Bundle savedInstanceState) {
		super.onPostCreate(savedInstanceState);
		// Sync the toggle state after onRestoreInstanceState has occurred.
		mDrawerToggle.syncState();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Pass any configuration change to the drawer toggls
		mDrawerToggle.onConfigurationChanged(newConfig);
	}

	public void goToGraphicPlanner(View view) {
		selectItem(0);
	}
	
	// ---------------------------------------------------
	// Listeners for AlertDialogs
	// ---------------------------------------------------
	
	// Clicked add semester
	public void AddSemester(View view) {
		DialogFragment newFragment = AddSemesterAlertDialogFragment
				.newInstance();
		newFragment.show(getFragmentManager(), "dialog");
	}
	
	// Adding actual semester
	public void AddSemesterToUser(int year, int semesterId)
	{
		FragmentManager fragmentManager = getFragmentManager();
		GraphicPlannerFragment fragment = (GraphicPlannerFragment) fragmentManager
				.findFragmentById(R.id.content_frame);

		if (fragment != null) {
			boolean result = GraphicPlannerRepository.AddSemester(fragment.model, year, semesterId);
			if(result)
			{
				Toast.makeText(getBaseContext(), "Added new semester successfuly",
						Toast.LENGTH_LONG).show();
			}
			else
			{
				Toast.makeText(getBaseContext(), "Error in adding a semester",
						Toast.LENGTH_LONG).show();
			}
		}
	}
	
	// Clicked delete course
	@Override
	public void onDialogPositiveClick(DialogFragment dialog, int groupPosition,
			int childPosition, CharSequence title, int itemId) {

		FragmentManager fragmentManager = getFragmentManager();
		GraphicPlannerFragment fragment = (GraphicPlannerFragment) fragmentManager
				.findFragmentById(R.id.content_frame);

		if (fragment != null) {

			UserCourse itemToRemove = GraphicPlannerRepository.GetItem(
					fragment.model, groupPosition, childPosition);

			if (itemId == itemToRemove.Id) {
				boolean result = GraphicPlannerRepository.RemoveItem(
						fragment.model, groupPosition, itemToRemove);
				if (result) {
					Toast.makeText(getBaseContext(),
							"Deleted " + title + " successfuly",
							Toast.LENGTH_LONG).show();
					return;
				}
			}
		}
		Toast.makeText(getBaseContext(), "Error in deleting " + title,
				Toast.LENGTH_LONG).show();
	}

	// clicked move semester
	@Override
	public void onDialogNegativeClick(DialogFragment dialog, int groupPosition,
			int childPosition, CharSequence title, int itemId) {
		FragmentManager fragmentManager = getFragmentManager();
		GraphicPlannerFragment fragment = (GraphicPlannerFragment) fragmentManager
				.findFragmentById(R.id.content_frame);

		if (fragment != null) {

			UserCourse itemToMove = GraphicPlannerRepository.GetItem(
					fragment.model, groupPosition, childPosition);

			if (itemId == itemToMove.Id) {
				DialogFragment newFragment = MoveSemesterAlertDialogFragment
						.newInstance(groupPosition, childPosition, title,
								itemId);
				newFragment.show(getFragmentManager(), "dialog");
			}
		} else {
			Toast.makeText(getBaseContext(),
					"Error in moving " + title + " semester.",
					Toast.LENGTH_LONG).show();
		}
	}

	// chosen semester to move course to
	public void MoveSemester(int groupPosition, int childPosition, int itemId,
			int toSemesterId) {
		
		FragmentManager fragmentManager = getFragmentManager();
		GraphicPlannerFragment fragment = (GraphicPlannerFragment) fragmentManager
				.findFragmentById(R.id.content_frame);

		if (fragment != null) {
			UserCourse itemToMove = GraphicPlannerRepository.GetItem(
					fragment.model, groupPosition, childPosition);
			if (itemId == itemToMove.Id) {
				boolean result = GraphicPlannerRepository.MoveItemSemester(
						fragment.model, groupPosition, childPosition,
						itemToMove, toSemesterId);
				if (result) {
					Toast.makeText(
							getBaseContext(),
							"Moved " + itemToMove.CourseName
									+ " semester successfuly",
							Toast.LENGTH_LONG).show();
					return;
				} else {
					Toast.makeText(
							getBaseContext(),
							"Error in moving " + itemToMove.CourseName
									+ " semester.", Toast.LENGTH_LONG).show();
				}
			}
		}
	}
}