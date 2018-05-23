package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.api.entity.Layout;

@Repository
public interface LayoutRepository extends JpaRepository<Layout, Integer> {

}
