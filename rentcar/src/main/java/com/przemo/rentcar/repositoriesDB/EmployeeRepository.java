package com.przemo.rentcar.repositoriesDB;

import com.przemo.rentcar.entities.users.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long> {
}
