package com.netdeal.passwordcheck.repositories;

import com.netdeal.passwordcheck.models.EmployeeModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeModel, UUID> {
    List<EmployeeModel> findByLevel(int level);
    List<EmployeeModel> findAllByOrderByScoreDesc();
}
