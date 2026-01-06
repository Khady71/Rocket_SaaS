package com.example.rocket_saas.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<UserAsso, Long> {
    @Query("SELECT u FROM UserAsso u WHERE u.email =?1")
    Optional<UserAsso> findUserByEmail(String email);
    boolean existsByEmail(String email);
    List<UserAsso> findByRole(Role role);
    List<UserAsso> findByAssociationId(Long associationId);
}
