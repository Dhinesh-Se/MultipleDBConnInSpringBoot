package com.kgisl.multipleconn2.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.kgisl.multipleconn2.Mapper.EmployeeRowMapper;
import com.kgisl.multipleconn2.model.Employee;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    @Qualifier("mysqlJdbcTemplate")
    private JdbcTemplate mysqlJdbcTemplate;

    @Autowired
    @Qualifier("mysqlJdbcTemplate2")
    private JdbcTemplate mysqJdbcTemplate2;

    public List<Employee> getAllEmployees() {
        // Use mysqJdbcTemplate2 to query data from the H2 database
        String query = "SELECT * FROM employee";
        return mysqJdbcTemplate2.query(query, new EmployeeRowMapper());
    }

    public Employee getEmployeeById(Long id) {
    String query = "SELECT * FROM employee WHERE id = ?";
    return mysqJdbcTemplate2.queryForObject(query, new EmployeeRowMapper(), id);
}


    public Employee createEmployee(Employee employee) {
        // Use mysqlJdbcTemplate to execute procedure in the MySQL database
        String procedureCall = "CALL create_employee(?)";
        mysqlJdbcTemplate.update(procedureCall, employee.getName());

        // Return the newly created employee (optional)
        return employee;
    }

    public Employee updateEmployee(Long id, Employee updatedEmployee) {
        // Use mysqlJdbcTemplate to execute procedure in the MySQL database
        String procedureCall = "CALL update_employee(?, ?)";
        mysqlJdbcTemplate.update(procedureCall, id, updatedEmployee.getName());

        // Return the updated employee (optional)
        return updatedEmployee;
    }

    public void deleteEmployee(Long id) {
        // Use mysqlJdbcTemplate to execute procedure in the MySQL database
        String procedureCall = "CALL delete_employee(?)";
        mysqlJdbcTemplate.update(procedureCall, id);
    }
}
