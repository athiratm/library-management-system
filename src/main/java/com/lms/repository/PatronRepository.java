package com.lms.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lms.model.Patron;



@Repository
public interface PatronRepository extends JpaRepository<Patron, Integer>{


}
