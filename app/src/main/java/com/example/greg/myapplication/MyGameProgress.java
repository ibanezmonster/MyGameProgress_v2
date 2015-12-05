package com.example.greg.myapplication;

import android.app.Application;
import android.content.ContentValues;

import java.util.ArrayList;

/**
 * Created by Greg on 11/9/2015.
 */
public class MyGameProgress extends Application {

    private ArrayList<Game> allGames;

    @Override
    public void onCreate(){
        super.onCreate();


    }

    public void addGame(Game game){
        assert game != null;
    }

    public ArrayList<Game> getAllGames(){
        return  allGames;
    }
}
