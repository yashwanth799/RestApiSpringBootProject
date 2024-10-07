package com.ivoyant.RestApiExample.configuration.testing;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.ivoyant.RestApiExample.model.Employee;
import com.ivoyant.RestApiExample.repository.EmployeeRepository;
import com.ivoyant.RestApiExample.service.EmployeeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.List;

@SpringBootTest
public class EmployeeServiceTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @InjectMocks
    private EmployeeService employeeService;

    private Employee employee;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        employee = new Employee(1, "John Doe", "Developer"); // Using int for id
    }

    @Test
    public void testGetEmployeeById() {
        // Mocking repository call
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        Optional<Employee> found = employeeService.getEmployeeById(1);
        assertTrue(found.isPresent());
        assertEquals(employee.getName(), found.get().getName());
    }

    @Test
    public void testCreateEmployee() {
        when(employeeRepository.save(employee)).thenReturn(employee);

        Employee saved = employeeService.createEmployee(employee);
        assertEquals(employee.getName(), saved.getName());
    }

    @Test
    public void testUpdateEmployee() {
        Employee updatedEmployee = new Employee(1, "Jane Doe", "Senior Developer");

        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));
        when(employeeRepository.save(any(Employee.class))).thenReturn(updatedEmployee);

        Employee result = employeeService.updateEmployee(1, updatedEmployee);
        assertNotNull(result);
        assertEquals(updatedEmployee.getName(), result.getName());
        assertEquals(updatedEmployee.getDepartment(), result.getDepartment());
    }

    @Test
    public void testDeleteEmployee() {
        when(employeeRepository.findById(1)).thenReturn(Optional.of(employee));

        boolean deleted = employeeService.deleteEmployee(1);
        assertTrue(deleted);
        verify(employeeRepository, times(1)).deleteById(1);
    }

    @Test
    public void testGetAllEmployees() {
        // In a real scenario, you'd mock a list of employees, but for simplicity, we just check the existing one.
        when(employeeRepository.findAll()).thenReturn(List.of(employee));

        List<Employee> foundEmployees = employeeService.getAllEmployees();
        assertFalse(foundEmployees.isEmpty());
        assertEquals(1, foundEmployees.size());
        assertEquals(employee.getName(), foundEmployees.get(0).getName());
    }
}
