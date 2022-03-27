package pl.oremczuk;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
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
        List <Employee> unsortedList = employeeRepository.findAll();
        printAll(unsortedList);

        List<Employee> sortedByFirstName = employeeRepository.findAll(Sort.by(Sort.Direction.ASC, "firstName"));
        logger.info("Sorted by firstname");
        printAll(sortedByFirstName);

        List<Employee> sortedByFirstNameAndLastName = employeeRepository.findAll(Sort.by(new Sort.Order(Sort.Direction.ASC, "firstName"), new Sort.Order(Sort.Direction.ASC, "lastName")));
        logger.info ("Sorted by firstname and lastname");
        printAll((sortedByFirstNameAndLastName));

        Page<Employee> page = employeeRepository.findAll(PageRequest.of(0, 10));
        logger.info("Total number of pages: " + page.getTotalPages());
        logger.info("Total number of elements: " + page.getTotalElements());
        printAll(page.getContent());
        logger.info("Current page: " + page.getNumber());
    }


    private void printAll(List<Employee> unsortedList) {

        unsortedList.forEach(employee -> logger.info(employee));
        // to to samo co: unsortedList.forEach(logger::info);

    }


}


