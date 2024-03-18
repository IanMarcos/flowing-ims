package com.ianmarcos.flowingims.repository;

import com.ianmarcos.flowingims.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TargetRepository extends JpaRepository<Target, String> {
}
