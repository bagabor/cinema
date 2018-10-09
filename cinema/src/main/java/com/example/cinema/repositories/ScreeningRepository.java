package com.example.cinema.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.cinema.model.Screening;

@Repository
public interface ScreeningRepository extends JpaRepository<Screening, Long>{
}
