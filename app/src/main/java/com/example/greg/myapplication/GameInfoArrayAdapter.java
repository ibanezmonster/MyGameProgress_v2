package com.example.greg.myapplication;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListAdapter;
import android.widget.TextView;

import java.util.ArrayList;
//test commit
/**
 * Created by Greg on 11/27/2015.
 */
public class GameInfoArrayAdapter extends BaseAdapter implements ListAdapter {
    private ArrayList<String> list = new ArrayList<String>();
    private Context context;
    protected static String currentGameName;

    public GameInfoArrayAdapter(ArrayList<String> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int pos) {
        return list.get(pos);
    }

    @Override
    public long getItemId(int pos) {
        return 0;//list.get(pos).getId();
        //just return 0 if your list items do not have an Id variable.
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        View view = convertView;

        if (view == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.dynamic_game_info_layout, null);
        }

        //Handle buttons and add onClickListeners
        Button characterBtn = (Button)view.findViewById(R.id.characterBtn);
        Button deleteCharacterBtn = (Button)view.findViewById(R.id.deleteCharacterBtn);
        TextView characterInfoTextView = (TextView)view.findViewById(R.id.characterInfoTextView);

        // Add character info to button and text view
        //for(String pos : list) {
            //display character name for button
            String name = list.get(position);
            name = name.substring(0, name.indexOf("\n"));
            characterBtn.setText(name);

            //display full character info for text view
            characterInfoTextView.setText(list.get(position));
        //}

        deleteCharacterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            //confirm delete
            new AlertDialog.Builder(context)
                    .setTitle("Delete Character")
                    .setMessage("Are you sure you want to delete this character?")
                    .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // continue with delete
                            list.remove(position);
                            GameInfoActivity.getCharacterList().remove(position);
                            notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            // do nothing
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert)
                    .show();
            }
        });

        return view;
    }
}
