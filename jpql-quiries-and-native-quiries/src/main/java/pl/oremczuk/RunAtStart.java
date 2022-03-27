package pl.oremczuk;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.math.BigDecimal;
import java.util.List;


@Component
public class RunAtStart {

    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = LogManager.getLogger(RunAtStart.class);

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
    }




    @PostConstruct
    public void runAtStart() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(employeeGenerator.generate());

        }


        List<Employee> allWhereSalaryBetweenValues = employeeRepository.findAllWhereSalaryBetweenValues(new BigDecimal("2000.0"), new BigDecimal("3000.0"));
        printAll(allWhereSalaryBetweenValues);

        List<Employee> employeesWithHighestSalary = employeeRepository.findEmployeesWithHighestSalary();
        printAll(employeesWithHighestSalary);

        Employee oneEmployeeWithHighestSalary = employeeRepository.findOneEmployeeWithHighestSalary();
        logger.info("First employee with highest salary: " + oneEmployeeWithHighestSalary);

        List<Employee> employeesWithSalaryBetween = employeeRepository.findEmployeesWithSalaryBetween(new BigDecimal("3000.0"), new BigDecimal("4000.0"));
        printAll(employeesWithSalaryBetween);


    }


    private void printAll(List<Employee> unsortedList) {

        unsortedList.forEach(employee -> logger.info(employee));
        // to to samo co: unsortedList.forEach(logger::info);

    }


}


