package me.riddhi.gada.olcademy;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.Calendar;

public class About extends AppCompatActivity {

    Dialog editProfileDialog, addEduDialog, addExpDialog, addAchDialog, addSkillsDialog, addInterestsDialog;
    TextView txtEditProfile, txtAddExp, txtAddEdu, txtclose, editDOB, txtEndYear, txtAddAwards, txtAddSkills, txtAddInterests ;
    Button btnUpdate, btnAdd ;
    CheckBox chkEndYear;
    EditText editEndYear;
    private DatePickerDialog.OnDateSetListener mDateSetListener;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.student_about);
        getSupportActionBar().setTitle("About");

        editProfileDialog = new Dialog(this);
        addExpDialog = new Dialog(this);
        addEduDialog = new Dialog(this);
        addAchDialog = new Dialog(this);
        addSkillsDialog = new Dialog(this);
        addInterestsDialog = new Dialog(this);

        txtEditProfile = (TextView) findViewById(R.id.editProfile);
        txtEditProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditProfile_ShowPopup(view);
            }
        });

        txtAddExp = (TextView) findViewById(R.id.addexp);
        txtAddExp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddExp_ShowPopup(view);
            }
        });

        txtAddEdu = (TextView) findViewById(R.id.addEdu);
        txtAddEdu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddEdu_ShowPopup(view);
            }
        });

        txtAddAwards = (TextView) findViewById(R.id.addAwards);
        txtAddAwards.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddAwards_ShowPopup(view);
            }
        });

        txtAddSkills = (TextView) findViewById(R.id.addSkills);
        txtAddSkills.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSkills_ShowPopup(view);
            }
        });

        txtAddInterests = (TextView) findViewById(R.id.addInterests);
        txtAddInterests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddInterests_ShowPopup(view);
            }
        });

    }

    public void EditProfile_ShowPopup(View v) {
        editProfileDialog.setContentView(R.layout.editprofile_popup);
        txtclose = (TextView) editProfileDialog.findViewById(R.id.txtclose);
        btnUpdate = (Button) editProfileDialog.findViewById(R.id.btnUpdate);
        editDOB = (TextView) editProfileDialog.findViewById(R.id.editDOB);

        editDOB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar cal = Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog dialog = new DatePickerDialog(
                        About.this,
                        android.R.style.Theme_Holo_Light_Dialog_MinWidth,
                        mDateSetListener,
                        year,month,day);
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month + 1;
                String date = day + "/" + month + "/" + year;
                editDOB.setText(date);
            }
        };

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editProfileDialog.dismiss();
            }
        });

        editProfileDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        editProfileDialog.show();
    }

    public void AddExp_ShowPopup(View v) {
        addExpDialog.setContentView(R.layout.addexp_popup);
        txtclose = (TextView) addExpDialog.findViewById(R.id.txtclose);
        chkEndYear = (CheckBox) addExpDialog.findViewById(R.id.chkEndYear);
        txtEndYear = (TextView) addExpDialog.findViewById(R.id.txtEndYear);
        editEndYear = (EditText) addExpDialog.findViewById(R.id.editEndYear);
        btnAdd = (Button) addExpDialog.findViewById(R.id.btnAdd);

        chkEndYear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(chkEndYear.isChecked()){
                    txtEndYear.setVisibility(View.GONE);
                    editEndYear.setVisibility(View.GONE);
                }
                else {
                    txtEndYear.setVisibility(View.VISIBLE);
                    editEndYear.setVisibility(View.VISIBLE);
                }
            }
        });

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addExpDialog.dismiss();
            }
        });

        addExpDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addExpDialog.show();
    }

    public void AddEdu_ShowPopup(View v) {
        addEduDialog.setContentView(R.layout.addedu_popup);
        txtclose = (TextView) addEduDialog.findViewById(R.id.txtclose);
        btnAdd = (Button) addEduDialog.findViewById(R.id.btnAdd);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addEduDialog.dismiss();
            }
        });

        addEduDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addEduDialog.show();
    }

    public void AddAwards_ShowPopup(View v) {
        addAchDialog.setContentView(R.layout.addawards_popup);
        txtclose = (TextView) addAchDialog.findViewById(R.id.txtclose);
        btnAdd = (Button) addAchDialog.findViewById(R.id.btnAdd);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAchDialog.dismiss();
            }
        });

        addAchDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addAchDialog.show();
    }

    public void AddSkills_ShowPopup(View v) {
        addSkillsDialog.setContentView(R.layout.addskills_popup);
        txtclose = (TextView) addSkillsDialog.findViewById(R.id.txtclose);
        btnAdd = (Button) addSkillsDialog.findViewById(R.id.btnAdd);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addSkillsDialog.dismiss();
            }
        });

        addSkillsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addSkillsDialog.show();
    }

    public void AddInterests_ShowPopup(View v) {
        addInterestsDialog.setContentView(R.layout.addinterests_popup);
        txtclose = (TextView) addInterestsDialog.findViewById(R.id.txtclose);
        btnAdd = (Button) addInterestsDialog.findViewById(R.id.btnAdd);

        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addInterestsDialog.dismiss();
            }
        });

        addInterestsDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        addInterestsDialog.show();
    }

}
