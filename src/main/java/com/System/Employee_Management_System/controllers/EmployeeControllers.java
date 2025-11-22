package com.System.Employee_Management_System.controllers;

import com.System.Employee_Management_System.entities.Employee;
import org.springframework.web.bind.annotation.*;

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
    public ArrayList<Employee> getEmployees() {
        return employees;
    }

    @GetMapping("/{employeeID}")
    public Optional<Employee> getEmployeeByID(@PathVariable UUID employeeID) {
        return employees.stream()
                .filter(emp -> emp.getId().equals(employeeID))
                .findFirst();
    }

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        employee.setId(UUID.randomUUID());
        employee.setDepartmentId(UUID.randomUUID());
        employees.add(employee);
        return employee;
    }

    @DeleteMapping("/{employeeID}")
    public Optional<Employee> deleteEmployee(@PathVariable UUID employeeID) {
        Optional<Employee> employee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeID))
                .findFirst();
        employee.ifPresent(emp -> employees.remove(emp));
        return employee;
    }

    @PutMapping("/{employeeID}")
    public Employee updateEmployee(@PathVariable UUID employeeID, @RequestBody Employee employee) {
        Optional<Employee> existingemployee = employees.stream()
                .filter(emp -> emp.getId().equals(employeeID))
                .findFirst();
        if (existingemployee.isPresent()) {
            existingemployee.get().setFristName(employee.getFristName());
            existingemployee.get().setLastName(employee.getLastName());
            existingemployee.get().setEmail(employee.getEmail());
            existingemployee.get().setPhoneNumber(employee.getPhoneNumber());
            existingemployee.get().setHireDate(employee.getHireDate());
            existingemployee.get().setPosition(employee.getPosition());
        }
        return employee;
    }

}
