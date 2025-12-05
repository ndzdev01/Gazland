package com.ndz.gazland.repository;

import com.ndz.gazland.models.GasBottle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GasBottleRepository extends JpaRepository<GasBottle, Integer> {
}
