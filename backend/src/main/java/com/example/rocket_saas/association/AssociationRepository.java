package com.example.rocket_saas.association;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AssociationRepository extends JpaRepository<Association,Long> {

    Optional<Association> findAssociationBySigle(String sigle);

}
