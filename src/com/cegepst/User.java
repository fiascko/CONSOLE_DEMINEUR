package com.cegepst;

public class User {

    private String userName;
    int  userScore;

    public User(String userName, String userScore) {
        this.userName = userName;
        this.userScore = Integer.parseInt(userScore);
    }

    public String getUserName() {
        return userName;
    }

}