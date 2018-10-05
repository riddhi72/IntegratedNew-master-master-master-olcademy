package me.riddhi.gada.olcademy;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;

import me.riddhi.gada.olcademy.fragments.*;

public class MainActivity extends AppCompatActivity implements GoogleApiClient.OnConnectionFailedListener{

    Toolbar toolbar;
    public  static GoogleApiClient mGoogleApiClient;
    private static final int RC_SIGN_IN = 007;
    GoogleSignInOptions gso;
    String email_name, email_id, tempimage;
    Uri googlepic;
    Intent i;
    Bundle p;
    // int flag=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
      i = getIntent();
        p = i.getExtras();
        email_name= p.getString("ename");
        email_id= p.getString("eid");
        tempimage=p.getString("picmy");
        if (tempimage!=null) {
            googlepic = Uri.parse(tempimage);
        }
        /* Teacher t=new Teacher();
        View view=t.getView();
        if(view!=null)
        {
           TextView data= view.findViewById(R.id.UserName);
            data.setText(email_name);
        }
        else {
            Toast.makeText(getApplicationContext(), email_name, Toast.LENGTH_SHORT).show();
        }*/

        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();
        mGoogleApiClient = new GoogleApiClient.Builder( this)
                .enableAutoManage(this, this)
                .addApi(Auth.GOOGLE_SIGN_IN_API, gso)
                .build();

        BottomNavigationView bottomNavigationView = (BottomNavigationView)
                findViewById(R.id.bottom_navigation);
        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_home:
                                selectedFragment = CourseFragment.newInstance();
                                //CourseFragment fragment1=new CourseFragment();
                                //LoadFragment(fragment1);
                                break;
                                //Intent renuka=new Intent(MainActivity.this,renuka_main.class);
                                //   startActivity(renuka);
                            case R.id.action_notifs:
                                selectedFragment = Notifications.newInstance();
                                break;
                            case R.id.action_my_enroll:
                                selectedFragment = MyEnroll.newInstance();
                                break;
                            case R.id.action_forum:
                                selectedFragment = Forum.newInstance();
                                break;
                            case R.id.action_profile:
                                //check if logged-in user is teacher or student. display profile accordingly.
                                selectedFragment = Teacher.newInstance(email_id, email_name, googlepic);
                                //selectedFragment = Student.newInstance(email_id, email_name, googlepic);
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment_container, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        //Manually displaying the first fragment - one time only
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_container, MyEnroll.newInstance());
        transaction.commit();

        //Used to select an item programmatically
        bottomNavigationView.getMenu().getItem(2).setChecked(true);
    }


    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this).setIcon(android.R.drawable.ic_dialog_alert).setTitle("Exit")
                .setMessage("Are you sure you want to exit?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                        finishAffinity();
                        System.exit(0);
                    }
                }).setNegativeButton("No", null).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        MenuItem mSearch = menu.findItem(R.id.action_search);
        SearchView mSearchView = (SearchView) mSearch.getActionView();
        mSearchView.setQueryHint("Search");
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return true;
            }
        });

        return super.onCreateOptionsMenu(menu);
    }



    public static void signout() {
        Auth.GoogleSignInApi.signOut(mGoogleApiClient).setResultCallback(
                new ResultCallback<Status>() {
                    @Override
                    public void onResult(Status status) {

                    }

                    });
    }

    @Override
    public void onConnectionFailed(@NonNull ConnectionResult connectionResult) {

    }


    public boolean LoadFragment(Fragment fragment)
    {
        if(fragment!=null)
        {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.frame_container,fragment).commit();
            return true;
        }

        return false;
    }
}

