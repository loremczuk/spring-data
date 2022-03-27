package pl.oremczuk;

import org.springframework.data.repository.RepositoryDefinition;

import java.util.List;

@RepositoryDefinition(domainClass = Employee.class, idClass = Long.class)
public interface EmployeeRepositoryWithAnnotation {


    Employee save(Employee employee);
    List<Employee> findAll();

}
