package org.Ashwani.Emp_Project.Repository;

import org.Ashwani.Emp_Project.Entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity,Long> {
    //Make custom methods in this
}
