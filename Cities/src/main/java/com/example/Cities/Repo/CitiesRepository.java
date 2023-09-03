package com.example.Cities.Repo;

import com.example.Cities.Models.Cities;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CitiesRepository extends CrudRepository<Cities, Long> {
}
