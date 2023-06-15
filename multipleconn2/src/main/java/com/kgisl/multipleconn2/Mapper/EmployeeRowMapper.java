package com.kgisl.multipleconn2.Mapper;

import org.springframework.jdbc.core.RowMapper;

import com.kgisl.multipleconn2.model.Employee;

import java.sql.ResultSet;
import java.sql.SQLException;

public class EmployeeRowMapper implements RowMapper<Employee> {

    @Override
    public Employee mapRow(ResultSet rs, int rowNum) throws SQLException {
        Employee employee = new Employee();
        employee.setId(rs.getLong("id"));
        employee.setName(rs.getString("name"));
        // Set other properties of the employee as needed
        return employee;
    }
}
