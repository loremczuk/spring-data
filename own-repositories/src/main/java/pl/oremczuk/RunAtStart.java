package pl.oremczuk;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.List;


@Component
public class RunAtStart {

    private final EmployeeRepository employeeRepository;
    private final EmployeeGenerator employeeGenerator;
    private final Logger logger = LogManager.getLogger(RunAtStart.class);
    private final EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation;
//    private final JpaRepository jpaRepository;
//    private final EmployeeBaseRepository employeeBaseRepository;
    private final EmployeeRepositoryExtendingBaseRepository employeeRepositoryExtendingBaseRepository;

    @Autowired
    public RunAtStart(EmployeeRepository employeeRepository, EmployeeGenerator employeeGenerator, EmployeeRepositoryWithAnnotation employeeRepositoryWithAnnotation, JpaRepository jpaRepository, EmployeeBaseRepository employeeBaseRepository, EmployeeRepositoryExtendingBaseRepository employeeRepositoryExtendingBaseRepository) {
        this.employeeRepository = employeeRepository;
        this.employeeGenerator = employeeGenerator;
        this.employeeRepositoryWithAnnotation = employeeRepositoryWithAnnotation;
//        this.jpaRepository = jpaRepository;
//        this.employeeBaseRepository = employeeBaseRepository;
        this.employeeRepositoryExtendingBaseRepository = employeeRepositoryExtendingBaseRepository;
    }





    @PostConstruct
    public void runAtStart() {
        for (int i = 0; i < 100; i++) {
            employeeRepository.save(employeeGenerator.generate());

        }


        List<Employee> all = employeeRepositoryWithAnnotation.findAll();
        printAll(all);

    }


    private void printAll(List<Employee> unsortedList) {

        unsortedList.forEach(employee -> logger.info(employee));
        // to to samo co: unsortedList.forEach(logger::info);

    }


}


