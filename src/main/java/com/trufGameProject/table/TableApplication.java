package com.trufGameProject.table;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@SpringBootApplication
public class TableApplication {
	@Autowired
    JdbcTemplate jdbcTemplate;

	@GetMapping("/")
	public String index() {
		return "Greetings from Spring Boot!";
	}

	@GetMapping("/table/")
	public String listTables() {
		String sql = "SELECT * FROM myTable";
    
		List<TableEntity> listTables = jdbcTemplate.query(sql,
					BeanPropertyRowMapper.newInstance(TableEntity.class));
		String JSONString = "[";
		for (TableEntity tables : listTables) {
			if (!JSONString.equals("[")) {
				JSONString += ", ";
			}
			JSONString += tables.toJSON();
		}
		JSONString += "]";
		return JSONString;
	}

	@GetMapping("/table/{id}")
	public String tableInfo(@PathVariable String id) {
		String sql = "SELECT * FROM myTable WHERE tableId = ?";
    	TableEntity table = jdbcTemplate.queryForObject(sql,
        BeanPropertyRowMapper.newInstance(TableEntity.class), id);
    	
		return table.toJSON();
	}

	@GetMapping("/table/type/{tableStatus}")
	public String getTableofStatus(@PathVariable int tableStatus) {
		String sql = "SELECT * FROM myTable WHERE tableStatus = ?";
    	TableEntity table = jdbcTemplate.queryForObject(sql,
        BeanPropertyRowMapper.newInstance(TableEntity.class), tableStatus);
    	
		return table.toJSON();
	}

	@PostMapping("/table")
	public String createTable(@RequestBody TableInfo table) {
		String sql = "INSERT INTO myTable (tableId, tableStatus) VALUES (?, ?)";
        int result = jdbcTemplate.update(sql, table.getTableId(), table.getTableStatus());
        
        if (result > 0) {
            System.out.println("Insert successfully.");
        }
		return tableInfo(table.getTableId());
	}

	@DeleteMapping("/table/{id}")
	public void tableDelete(@PathVariable String id) {
		String sql = "DELETE FROM myTable WHERE tableId = ?";
    	jdbcTemplate.update(sql, id);
	}

	@PatchMapping("/table/{id}/position/{position}") 
	public void positionUpdate(@PathVariable String id, @PathVariable String position, @RequestBody PId pid) {
		String sql = "UPDATE myTable SET ";
		if (position.equals("North")) {sql += "North = ? WHERE tableId = ?";}
		else if (position.equals("West")) {sql += "West = ? WHERE tableId = ?";}
		else if (position.equals("South")) {sql += "South = ? WHERE tableId = ?";}
		else if (position.equals("East")) {sql += "East = ? WHERE tableId = ?";}
		else {/* Error */}
		jdbcTemplate.update(sql, pid.getPid(), id);
	}

	@PatchMapping("/table/{id}/status/{tableStatus}") 
	public void statusUpdate(@PathVariable String id, @PathVariable int tableStatus) {
		String sql = "UPDATE myTable SET tableStatus = ? WHERE tableId = ?";
        jdbcTemplate.update(sql, tableStatus, id);
	}

	public static void main(String[] args) {
		SpringApplication.run(TableApplication.class, args);
	}

} 
