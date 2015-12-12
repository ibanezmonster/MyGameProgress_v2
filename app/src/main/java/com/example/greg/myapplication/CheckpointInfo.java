package com.example.greg.myapplication;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.text.format.Time;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

/**
 * Created by Rob Bosse on 12/11/2015.
 */
public class CheckpointInfo extends Activity {
    Time today = new Time(Time.getCurrentTimezone());

    DBAdapter myDb;
    EditText edTask;


    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.checkpoint_info);

        edTask = (EditText) findViewById(R.id.editTask);
        openDB();
        populateListView();
    }

    private void openDB(){
        myDb = new DBAdapter(this);
        myDb.open();
    }

    public void onClick_AddTask (View v){
        today.setToNow();
        String timestamp = today.format("%y-%m-%d %H:%M:%S");
        if (!TextUtils.isEmpty(edTask.getText().toString())){
            myDb.insertRow(edTask.getText().toString(), timestamp);
        }
        populateListView();
    }

    private void populateListView(){
        Cursor cursor = myDb.getAllRows();
        String[] fromFieldNames = new String[] {DBAdapter.KEY_ROWID,DBAdapter.KEY_TASK};
        int[] toViewIds = new int[] {R.id.textViewItemNumber, R.id.textViewItemTask};
        SimpleCursorAdapter myCursorAdapter;
        myCursorAdapter = new SimpleCursorAdapter(getBaseContext(),R.layout.checkpoint_layout, cursor, fromFieldNames, toViewIds,0);
        ListView myList = (ListView) findViewById(R.id.listViewTasks);
        myList.setAdapter(myCursorAdapter);
    }
}
