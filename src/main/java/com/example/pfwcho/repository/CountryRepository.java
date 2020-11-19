package com.example.pfwcho.repository;

import com.example.pfwcho.entity.Country;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CountryRepository extends JpaRepository<Country,Integer> {

    List<Country> findAll();

    Country findById(int id);

    void deleteById(int id);
}