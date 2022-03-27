package pl.oremczuk;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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

        List<Employee> listOfLiams = employeeRepository.findByFirstNameIgnoreCase("lIam");
        printAll(listOfLiams);

        List<Employee> listofSalariesBetween = employeeRepository.findBySalaryBetween (new BigDecimal("2000.0"), new BigDecimal("3000.0"));
        printAll(listofSalariesBetween);

        List<Employee> noahsOrderedByLastNameDesc = employeeRepository.findByFirstNameOrderByLastNameDesc("Noah");
        printAll(noahsOrderedByLastNameDesc);

        Employee jackson = employeeRepository.findFirstByFirstName("Jackson");
        logger.info("First Jackson: " + jackson);
        Employee phil = employeeRepository.findFirstByFirstName("Phil");
        logger.info("First Jackson: " + phil);

        List<Employee> firstThreeLucas = employeeRepository.findTop3ByFirstName("Lucas");
        printAll(firstThreeLucas);

        List<Employee> firstThreeAidens = employeeRepository.findFirst3ByFirstName("Aiden");
        printAll(firstThreeAidens);

        int jacksonTaylorsNumber = employeeRepository.countByFirstNameAndLastNameIgnoreCase("JacKson", "taYlor");
        logger.info("Number of Jackson Taylors: " + jacksonTaylorsNumber);

        Page<Employee> liamsOnPages = employeeRepository.findByFirstName("Liam", PageRequest.of(1, 5));
        logger.info("Liams on page: " + liamsOnPages.getNumber() + " : " + liamsOnPages.getContent());
        logger.info("Total number of pages: " + liamsOnPages.getTotalPages());
        logger.info("Total number of Liams: " + liamsOnPages.getTotalElements());

//        Stream<Employee> streamOfBrowns = employeeRepository.findTop10ByLastName("Brown");
//        List<String> listOfBrowns = streamOfBrowns.map(Employee::getLastName).collect(Collectors.toList());
//        nie działa - coś z transakcjami do streamów

        employeeRepository.findFirstByLastName("Smith").thenAccept(smith -> {
            logger.info("Smith: " + smith);
                }
        );



    }


    private void printAll(List<Employee> unsortedList) {

        unsortedList.forEach(employee -> logger.info(employee));
        // to to samo co: unsortedList.forEach(logger::info);

    }


}


