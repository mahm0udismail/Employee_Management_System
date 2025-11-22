package com.System.Employee_Management_System.controllers;

import com.System.Employee_Management_System.entities.Employee;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/employee")
public class EmployeeControllers {

    ArrayList<Employee> employees = new ArrayList<>(
            List.of(
                    new Employee(
                            UUID.randomUUID(),
                            "Mahmoud",
                            "Ismail",
                            "MahmoudIsmail@example.com",
                            "+201234567891",
                            LocalDate.of(2024, 12, 25),
                            "Software Engineer",
                            UUID.randomUUID()
                    ),
                    new Employee(
                            UUID.randomUUID(),
                            "Sara",
                            "Mohamed",
                            "SaraMohamed@example.com",
                            "+201234567892",
                            LocalDate.of(2023, 5, 10),
                            "HR Manager",
                            UUID.randomUUID()
                    )
            )
    );

    @GetMapping("")
    public ArrayList<Employee> getEmployees(){
        return employees;
    }

    @GetMapping("/{employeeID}")
    public Optional<Employee> getEmployeeByID(@PathVariable UUID employeeID){
        return employees.stream()
                .filter(emp -> emp.getId().equals(employeeID))
                .findFirst();
    }
}
