package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Domain;

@Repository
public interface DomainRepository extends JpaRepository<Domain, Integer> {

}
