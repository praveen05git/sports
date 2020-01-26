package com.hencesimplified.sandsports;

public class tournament_cls {

    String t_name;
    String game;


    public tournament_cls() {
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

    public tournament_cls(String t_name, String game) {
        this.t_name = t_name;
        this.game = game;
    }


}
