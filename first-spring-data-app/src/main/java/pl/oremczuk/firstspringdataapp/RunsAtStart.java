package pl.oremczuk.firstspringdataapp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class RunsAtStart {

    private EmployeeRepository employeeRepository;

    @Autowired
    public RunsAtStart(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }
    @PostConstruct
    public void runsAtStart () {
        Employee employee = new Employee();
        employee.setFirstName("Władysław");
        employee.setLastName("Kabaczek");
        employee.setSalary(new BigDecimal("5000.0"));
        employee.setEmploymentDate(LocalDate.of(2021,7,21));

        employeeRepository.save(employee);

//        Iterable<Employee> władki = employeeRepository.findByFirstName("Władysław");
//        Employee władek = władki.iterator().next();
//        System.out.println(władek);

        List<Employee> władki = employeeRepository.findByFirstName("Władysław");
        Employee władek = władki.get(0);
        System.out.println(władek);



    }

}
