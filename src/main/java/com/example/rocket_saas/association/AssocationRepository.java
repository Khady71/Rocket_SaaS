package com.example.rocket_saas.association;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AssocationRepository extends JpaRepository<Association,Long> {

}
