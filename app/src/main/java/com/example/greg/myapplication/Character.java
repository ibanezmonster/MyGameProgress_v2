package com.example.greg.myapplication;

import java.util.ArrayList;

/**
 * Created by Greg on 11/27/2015.
 */
public class Character {
    private String name;
    private String race;
    private int characterLevel;
    private ArrayList<String> job;
    private ArrayList<Integer> jobLevel;
    private String fullInfo;

    public Character(String name, String race, int characterLevel, ArrayList<String> job, ArrayList<Integer> jobLevel, String fullInfo) {
        this.name = name;
        this.race = race;
        this.characterLevel = characterLevel;
        this.job = job;
        this.jobLevel = jobLevel;
        this.fullInfo = fullInfo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public int getCharacterLevel() {
        return characterLevel;
    }

    public void setCharacterLevel(int characterLevel) {
        this.characterLevel = characterLevel;
    }

    public ArrayList<String> getJob() {
        return job;
    }

    public void setJob(ArrayList<String> job) {
        this.job = job;
    }

    public ArrayList<Integer> getJobLevel() {
        return jobLevel;
    }

    public void setJobLevel(ArrayList<Integer> jobLevel) {
        this.jobLevel = jobLevel;
    }

    public String getFullInfo() {
        return fullInfo;
    }

    public void setFullInfo(String fullInfo) {
        this.fullInfo = fullInfo;
    }
}
