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

API reference (all results are in JSON):

- `GET /table/` list tableIds and status
- `GET /table/{id}` detail table information
- `GET /table/type/{tableStatus}` table of specific types
- `POST /table` new table
- `DELETE /table/{id}` delete the table
- `PATCH /table/{id}/position/{position}` - data used: `player: {pid}` adding a player to a certain position (NWSE)
- `PATCH /table/{id}/status/{tableStatus}` change the table status
