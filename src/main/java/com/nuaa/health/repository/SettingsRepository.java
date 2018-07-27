package com.nuaa.health.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.nuaa.health.entity.Settings;

@Repository
public interface SettingsRepository extends CrudRepository<Settings, Integer>{
    Boolean existsByid(Integer id);
    Settings findByid(Integer id);
}