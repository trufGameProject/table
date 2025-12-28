package com.trufGameProject.table;

public class TableEntity {
    private String tableId;
    private int tableStatus;
    private String North;
    private String West;
    private String South;
    private String East;
    private int MaxScore;
    private int RuleType;
    private int ScoreNorth;
    private int ScoreWest;
    private int ScoreSouth;
    private int ScoreEast;
 
    protected TableEntity(int tableStatus, String North, String West, String South, String East, int MaxScore, int RuleType, int ScoreNorth, int ScoreWest, int ScoreSouth, int ScoreEast) {
        this.tableStatus = tableStatus;
        this.North = North;
        this.West = West;
        this.South = South;
        this.East = East;
        this.MaxScore = MaxScore;
        this.RuleType = RuleType;
        this.ScoreNorth = ScoreNorth;
        this.ScoreWest = ScoreWest;
        this.ScoreSouth = ScoreSouth;
        this.ScoreEast = ScoreEast;
    }
 
    protected TableEntity() {
    }

    public String toJSON() {
        return "{ \"tableId\": \"" + getTableId() + "\", \"tableStatus\": " + getTableStatus()
        + "\", \"North\": " + getNorth() + "\", \"West\": " + getWest() + "\", \"South\": " + getSouth() + "\", \"East\": " + getEast()
        + "\", \"MaxScore\": " + getMaxScore() + "\", \"RuleType\": " + getRuleType()
        + "\", \"ScoreNorth\": " + getScoreNorth() + "\", \"ScoreWest\": " + getScoreWest() + "\", \"ScoreSouth\": " + getScoreSouth() + "\", \"ScoreEast\": " + getScoreEast();
    }

    @Override
    public String toString() {
        return toJSON();
    }
 
    // getters and setters...
    public String getTableId() {
        return tableId;
    }

    public int getTableStatus() {
        return tableStatus;
    }

    public void setTableStatus(int set) {
        tableStatus = set;
    }

    public String getNorth() {
        return North;
    }

    public String getWest() {
        return West;
    }

    public String getSouth() {
        return South;
    }

    public String getEast() {
        return East;
    }

    public int getMaxScore() {
        return MaxScore;
    }

    public void setMaxScore(int set) {
        MaxScore = set;
    }

    public int getRuleType() {
        return RuleType;
    }

    public int getScoreNorth() {
        return ScoreNorth;
    }

    public void setScoreNorth(int set) {
        ScoreNorth = set;
    }

    public int getScoreWest() {
        return ScoreWest;
    }

    public void setScoreWest(int set) {
        ScoreWest = set;
    }

    public int getScoreSouth() {
        return ScoreSouth;
    }

    public void setScoreSouth(int set) {
        ScoreSouth = set;
    }

    public int getScoreEast() {
        return ScoreEast;
    }

    public void setScoreEast(int set) {
        ScoreEast = set;
    }
}
