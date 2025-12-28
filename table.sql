CREATE TABLE [table] (
    -- Table ID
    tableId CHAR(32) PRIMARY KEY,
    
    -- tableStatus: 0 = new - 1 = waiting - 2 = playing - 3 = done - 4 = abandoned)
    tableStatus INT NOT NULL CHECK (tableStatus BETWEEN 0 AND 4),

    -- Players ref to playerId
    North VARCHAR(80) DEFAULT '',
    West VARCHAR(80) DEFAULT '',
    South VARCHAR(80) DEFAULT '',
    East VARCHAR(80) DEFAULT '',
    
    -- Score
    MaxScore INT DEFAULT 0,
    RuleType INT DEFAULT 0,  -- (regular = 0 for now)

    ScoreNorth INT DEFAULT 0,
    ScoreWest INT DEFAULT 0,
    ScoreSouth INT DEFAULT 0,
    ScoreEast INT DEFAULT 0,
);