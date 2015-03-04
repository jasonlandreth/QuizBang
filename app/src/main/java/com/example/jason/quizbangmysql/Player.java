package com.example.jason.quizbangmysql;

/**
 * Created by Jason on 2/22/2015.
 */
/**
 * Created by Jason on 2/22/2015.
 */
public class Player {

    private String player_name;
    private int avatar_id;

    public Player(String player_name, int avatar_id) {
        super();
        this.player_name = player_name;
        this.avatar_id = avatar_id;
    }

    public String getName() {
        return player_name;
    }

    public int getAvatar_id() {
        return avatar_id;
    }

    @Override
    public String toString() {

        return player_name;
    }
}