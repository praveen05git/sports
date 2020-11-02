package com.hencesimplified.sandsports;

public class TournamentClass {

    String t_name;
    String game;


    public TournamentClass() {
    }

    public String getT_name() {
        return t_name;
    }

    public void setT_name(String t_name) {
        this.t_name = t_name;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    public TournamentClass(String t_name, String game) {
        this.t_name = t_name;
        this.game = game;
    }


}
