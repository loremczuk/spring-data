package pl.oremczuk.firstspringdataapp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
// public interface EmployeeRepository extends CrudRepository<Employee, Long> {
    public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    // Iterable<Employee> findByFirstName (String firstName);
    List<Employee> findByFirstName (String firstName);
}
