package com.example.greg.myapplication;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private static ArrayList<Game> gameListGames;
    private Button btnGame;
    private Context context;
    private ArrayList<String> list;
    private MainActivity mainActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_list_layout);
        Log.d("Starting", "...");

        MyGameProgress app = (MyGameProgress) getApplication();
        gameListGames = app.getAllGames();
        Log.d("Game list", "Got game list");

        list = new ArrayList<String>();
        gameListGames = new ArrayList<>();

        //show dialog box by default each time it runs, unless user chooses to not see it again
        final SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        final CheckBox cb = new CheckBox(this);
        cb.setText("Do not show again");

        //SharedPreferences.Editor edit = preferences.edit();
        //edit.putBoolean("showDialog", true).commit();

        if(preferences.getBoolean("showDialog", true)){
            new AlertDialog.Builder(this)
                    .setTitle("My Game Progress")
                    .setMessage("Welcome to My Game Progress! Simply select \"Add Game\" to add a new game.")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichbutton) {
                            if(cb.isChecked()) {
                                SharedPreferences.Editor edit = preferences.edit();
                                edit.putBoolean("showDialog", false).commit();
                            }
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int whichbutton) {
                            //do nothing
                        }
                    })
                    .setView(cb)
                    .show();
        }

        //if no entries in game list, display a message stating that entries need to be added
//        if(gameListGames.isEmpty()){
//            ListView gamesListView = (ListView) findViewById(R.id.gamesListView);
//            gamesListView.setVisibility(View.INVISIBLE);
//
//            TextView noEntries = new TextView(this);
//            noEntries.setText("No entries");
//            gamesListView.addView(noEntries);
//        }

        //if there are entries, generate list
        //else {
            displayList();
        //}



        //set button onClick Listener
        setContentView(R.layout.game_list_layout);
        Button addBtn = (Button) findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                newGameClickHandler();
            }
        });
    }

    public void newGameClickHandler(){
        final EditText input = new EditText(this);

        new AlertDialog.Builder(this)
                .setTitle("Name of Game")
                .setMessage("What is the Name of the Game?")
                .setView(input)
                .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichbutton) {
                        String gameName = input.getText().toString();
                        addToGameList(gameName);
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichbutton) {
                        //do nothing
                    }
                })
                .show();
    }

    public void addToGameList(String gameName){
        gameListGames.add(new Game(gameName));
        list.add(gameListGames.get(gameListGames.size() - 1).getName());
        displayList();
    }

    public void displayList(){
        //instantiate custom adapter
        GameArrayAdapter adapter = new GameArrayAdapter(list, this);

        //handle listview and assign adapter
        ListView lView = (ListView)findViewById(R.id.gamesListView);
        lView.setAdapter(adapter);
    }


    public static ArrayList<Game> getGameList() {
        return gameListGames;
    }
}

