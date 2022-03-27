package pl.oremczuk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;


public interface EmployeeRepository extends JpaRepository <Employee, Long> {

    List<Employee> findAllWhereSalaryBetweenValues (BigDecimal from, BigDecimal to);

    @Query(/* value= */ "SELECT e FROM Employee e WHERE e.salary = (SELECT MAX(em.salary) FROM Employee em)")
    List<Employee> findEmployeesWithHighestSalary();
    // lista bo może być więcej niż jeden pracownik z tą samą najwyższą pensją

    @Query(value = "SELECT * FROM employee WHERE salary = (SELECT MAX(salary) FROM employee) LIMIT 1",
            nativeQuery = true)
    Employee findOneEmployeeWithHighestSalary();

    @Query (value = "SELECT * FROM employee WHERE salary BETWEEN :from  AND :to",
            nativeQuery = true)
//    List<Employee> findEmployeesWithSalaryBetween (@Param("from") BigDecimal from, @Param("to") BigDecimal to);
    // teraz działa już bez podawania adnotacji @Param
    List<Employee> findEmployeesWithSalaryBetween (BigDecimal from, BigDecimal to);

}
