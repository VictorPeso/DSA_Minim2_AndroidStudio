package edu.upc.dsa.dsa_error404_android;

public class TeamRanking {
    private String name;
    private String avatar;
    private int points;

    public TeamRanking() {}

    public TeamRanking(String name, String avatar, int points) {
        this.name = name;
        this.avatar = avatar;
        this.points = points;
    }
    public String getName() {
        return name;
    }

    public String getAvatar() {
        return avatar;
    }

    public int getPoints() {
        return points;
    }
}

