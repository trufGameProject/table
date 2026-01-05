package com.trufGameProject.table;

public class TableEntity {
    private String tableid;
    private int tablestatus;
    private String north;
    private String west;
    private String south;
    private String east;
    private int maxscore;
    private int ruletype;
    private int scorenorth;
    private int scorewest;
    private int scoresouth;
    private int scoreeast;
 
    protected TableEntity(int tablestatus, String north, String west, String south, String east, int maxscore, int ruletype, int scorenorth, int scorewest, int scoresouth, int scoreeast) {
        this.tablestatus = tablestatus;
        this.north = north;
        this.west = west;
        this.south = south;
        this.east = east;
        this.maxscore = maxscore;
        this.ruletype = ruletype;
        this.scorenorth = scorenorth;
        this.scorewest = scorewest;
        this.scoresouth = scoresouth;
        this.scoreeast = scoreeast;
    }
 
    protected TableEntity() {
    }

    public String toJSON() {
        return "{ \"tableId\": \"" + getTableId() + "\", \"tableStatus\": " + getTableStatus()
        + ", \"North\": \"" + getNorth() + "\", \"West\": \"" + getWest() + "\", \"South\": \"" + getSouth() + "\", \"East\": \"" + getEast()
        + "\", \"MaxScore\": " + getMaxScore() + ", \"RuleType\": " + getRuleType()
        + ", \"ScoreNorth\": " + getScoreNorth() + ", \"ScoreWest\": " + getScoreWest() + ", \"ScoreSouth\": " + getScoreSouth() + ", \"ScoreEast\": " + getScoreEast() + "}";
    }

    @Override
    public String toString() {
        return toJSON();
    }
 
    // getters and setters...
    public String getTableId() {
        return tableid;
    }

    public void setTableId(String set) {
        tableid = set;
    }

    public int getTableStatus() {
        return tablestatus;
    }

    public void setTableStatus(int set) {
        tablestatus = set;
    }

    public String getNorth() {
        return north;
    }

    public void setNorth(String set) {
        north = set;
    }

    public String getWest() {
        return west;
    }

    public void setWest(String set) {
        west = set;
    }

    public String getSouth() {
        return south;
    }

    public void setSouth(String set) {
        south = set;
    }

    public String getEast() {
        return east;
    }

    public void setEast(String set) {
        east = set;
    }

    public int getMaxScore() {
        return maxscore;
    }

    public void setMaxScore(int set) {
        maxscore = set;
    }

    public int getRuleType() {
        return ruletype;
    }

    public int getScoreNorth() {
        return scorenorth;
    }

    public void setScoreNorth(int set) {
        scorenorth = set;
    }

    public int getScoreWest() {
        return scorewest;
    }

    public void setScoreWest(int set) {
        scorewest = set;
    }

    public int getScoreSouth() {
        return scoresouth;
    }

    public void setScoreSouth(int set) {
        scoresouth = set;
    }

    public int getScoreEast() {
        return scoreeast;
    }

    public void setScoreEast(int set) {
        scoreeast = set;
    }
}
