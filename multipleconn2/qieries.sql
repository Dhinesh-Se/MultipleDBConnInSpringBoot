
-- for create a procedure
CREATE PROCEDURE create_employee (
  IN employee_name VARCHAR(255)
)
BEGIN
  -- Add your logic here
  -- For example, you can insert the employee into a table
  INSERT INTO employee (name) VALUES (employee_name);
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE update_employee (
  IN employee_id INT,
  IN new_name VARCHAR(255)
)
BEGIN
  -- Add your logic here
  -- For example, you can update the employee name in the table
  UPDATE employee SET name = new_name WHERE id = employee_id;
END //

DELIMITER ;
DELIMITER //

CREATE PROCEDURE delete_employee (
  IN employee_id INT
)
BEGIN
  -- Add your logic here
  -- For example, you can delete the employee from the table
  DELETE FROM employee WHERE id = employee_id;
END //

DELIMITER ;