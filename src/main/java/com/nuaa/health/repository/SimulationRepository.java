package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Simulation;

@Repository
public interface SimulationRepository extends CrudRepository<Simulation, Long> {
	boolean existsByUserId(Long uid);
	Simulation findByUserId(Long uid);
}
