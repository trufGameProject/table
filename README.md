# Application for Player REST API interface

Repository content:

- Database structure and initialization: [database/README.md](database/README.md)
- Source code [python/README.md](python/README.md)
- Kubernetes artifacts [k8s/README.md](k8s/README.md)

Database structure:

```
tableId: char(32)
tableStatus: int (new - waiting - playing - done - abandoned)
North: varchar(50) > ref to playerId
West: varchar(50) > ref to playerId
South: varchar(50) > ref to playerId
East: varchar(50) > ref to playerId
MaxScore: int
RuleType: int (regular = 0 for now)
ScoreNorth: int
ScoreWest: int
ScoreSouth: int
ScoreEast: int
```

API reference:

- `GET /table/` list tableIds and status
- `GET /table/{id}` detail table information
- `GET /table/?type={type}` table of specific types
- `POST /table` new table
- `PATCH /table/{id}/player/{pid}` adding a player to a certain position (NWSE)
