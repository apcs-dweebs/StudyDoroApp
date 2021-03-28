package com.example.tabbed;

public class Goal {
    private int importance;
    private int time;
    private String word;

    public Goal() {
        this("goal1", 1, 0);
    }

    public Goal(String theWord,  int tim, int imp) {
        importance = imp;
        time = tim;
        word = theWord;
    }

    public int getImp() {
        return importance;
    }

    public int getTime() {
        return time;
    }

    public String getWord() {
        return word;
    }

    public void setImp(int newImp) {
        importance = newImp;
    }

    public void setTime(int newTime) {
        time = newTime;
    }


    public void setWord(String newWord) {
        word = newWord;
    }
}
