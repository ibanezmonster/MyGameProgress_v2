package com.example.greg.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Greg on 11/18/2015.
 */
public class GameInfoActivity extends Activity {
    private ArrayList<String> list;
    private ArrayList<Integer> jobs;
    private AlertDialog ad;
    private static ArrayList<Character> characterList;
    private static String currentGameName;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_info_layout);


        list = new ArrayList<>();
        characterList = new ArrayList<>();
        jobs = new ArrayList<>();

        //Set the title of the activity to the name of the game that was selected
        currentGameName = GameArrayAdapter.currentGameName;
        TextView txtGameName = (TextView) (findViewById(R.id.txtGameName));
        txtGameName.setText(currentGameName);

        //set button onClick Listener
        Button addCharacterBtn = (Button) findViewById(R.id.addCharacterBtn);
        addCharacterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addCharacterClickHandler();
            }
        } );
    }




    public void addCharacterClickHandler() {
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.character_creator_dialog, null);

        final EditText name = (EditText) textEntryView.findViewById(R.id.nameEditText);
        final EditText characterLevel = (EditText) textEntryView.findViewById(R.id.levelEditText);
        final EditText race = (EditText) textEntryView.findViewById(R.id.raceEditText);
        final EditText jobName = (EditText) textEntryView.findViewById(R.id.jobNameEditText);
        final EditText jobLevel = (EditText) textEntryView.findViewById(R.id.jobLevelEditText);
        final RadioButton clYes = (RadioButton) textEntryView.findViewById(R.id.characterLevelYesRadioButton);
        final RadioButton clNo = (RadioButton) textEntryView.findViewById(R.id.characterLevelNoRadioButton);

        final RadioGroup characterLevelYesNo = (RadioGroup) textEntryView.findViewById(R.id.characterLevelYesNoRadioGroup);

        //set up character creator dialog box
        //make sure that character level isn't editable by default
        characterLevel.setFocusableInTouchMode(false);
        characterLevel.setFocusable(false);
        clNo.setChecked(true);

        //control ability to edit the character level
        characterLevelYesNo.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                int id = characterLevelYesNo.getCheckedRadioButtonId();
                View radioButton = characterLevelYesNo.findViewById(id);
                int radioId = characterLevelYesNo.indexOfChild(radioButton);
                RadioButton btn = (RadioButton) characterLevelYesNo.getChildAt(radioId);
                String selection = (String) btn.getText();

                if (selection.equals("Yes")) {
                    characterLevel.setFocusableInTouchMode(true);
                    characterLevel.setFocusable(true);
                } else if (selection.equals("No")) {
                    characterLevel.setFocusableInTouchMode(false);
                    characterLevel.setFocusable(false);
                }
            }
        });

        //display character creator dialog box
         ad = new AlertDialog.Builder(this)
                 .setTitle("Add Character")
                 .setMessage("Create Character Info")
                 .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         //get input values
                         String nameVal;
                         int characterLevelVal;
                         String raceVal;
                         ArrayList<String> jobNamesVal = new ArrayList<String>();
                         ArrayList<Integer> jobLevelsVal = new ArrayList<Integer>();

                         nameVal = name.getText().toString();
                         characterLevelVal = -1;

                         if(!characterLevel.getText().toString().isEmpty()) {
                             characterLevelVal = Integer.parseInt(characterLevel.getText().toString());
                         }

                         raceVal = race.getText().toString();
                         jobNamesVal.add(jobName.getText().toString());

                         if(!jobLevel.getText().toString().isEmpty()) {
                             jobLevelsVal.add(Integer.parseInt(jobLevel.getText().toString()));
                         }

                         //add character
                         addToCharacterList(nameVal, raceVal, characterLevelVal, jobNamesVal, jobLevelsVal);
                     }
                 })
                 .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                     public void onClick(DialogInterface dialog, int which) {
                         // do nothing
                     }
                 })
                 .setIcon(android.R.drawable.ic_dialog_alert)
                 .setView(textEntryView)
                 .show();

        //set button onClick Listener
        Button addAnotherJobButton = (Button) ad.findViewById(R.id.addAnotherJobButton);
        addAnotherJobButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAnotherJobClickHandler();
            }
        });
    }


    public void addAnotherJobClickHandler(){
        //instantiate custom adapter
//        jobs.add(0, 0);
//        JobArrayAdapter adapter = new JobArrayAdapter(jobs, ad.getContext());
//
//        //handle listview and assign adapter
//        ListView jobsListView = (ListView)findViewById(R.id.jobsListView);
//        jobsListView.setAdapter(adapter);
    }


    public static ArrayList<Character> getCharacterList() {
        return characterList;
    }

    public void addToCharacterList(String name, String race, int characterLevel, ArrayList<String> job, ArrayList<Integer> jobLevel){
        //build character information text
        String fullInfo = name +  "\n" + race + "\n";

        if(characterLevel != -1){
            fullInfo += "Level " + characterLevel + "\n";
        }

        fullInfo += job.get(0) + " " + jobLevel.get(0);

        //add character
        characterList.add(new Character(name, race, characterLevel, job, jobLevel, fullInfo));
        list.add(characterList.get(characterList.size() - 1).getFullInfo());
        displayList();
    }

    public void displayList(){
        //instantiate custom adapter
        GameInfoArrayAdapter adapter = new GameInfoArrayAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.gameInfoListView);
        lView.setAdapter(adapter);
    }


}