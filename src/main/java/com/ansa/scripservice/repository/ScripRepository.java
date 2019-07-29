package com.ansa.scripservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ansa.scripservice.entity.Scrip;

@Repository
public interface ScripRepository extends JpaRepository<Scrip,Long>{
  
}
