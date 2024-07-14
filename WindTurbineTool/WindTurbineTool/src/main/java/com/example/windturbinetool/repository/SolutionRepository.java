package com.example.windturbinetool.repository;

import com.example.windturbinetool.model.Solution;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface SolutionRepository extends JpaRepository<Solution, Long> {
    List<Solution> findByFaultCodeNumberContainingIgnoreCase(String faultCodeNumber);
}