package pl.oremczuk;

import org.springframework.data.repository.Repository;

import java.util.List;


public interface EmployeeRepository extends Repository<Employee, Long> {


    Employee save(Employee employee);
    List<Employee> findAll();

}
