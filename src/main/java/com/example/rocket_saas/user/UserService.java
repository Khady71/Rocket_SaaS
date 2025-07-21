package com.example.rocket_saas.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<UserAsso> getUsers() {
        return userRepository.findAll();
    }

    public void addNewUser(UserAsso user) {
        Optional<UserAsso>userOptional =  userRepository
                .findUserByEmail(user.getEmail());
        if(userOptional.isPresent()) {
            throw new IllegalStateException("email taken");
        }
        userRepository.save(user);
        System.out.println(user);
    }

    public UserAsso getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));
    }



    // ✅ Mettre à jour un utilisateur
    public UserAsso updateUser(Long id, UserAsso updatedUser) {
        UserAsso existingUser = userRepository.findById(id)
                .orElseThrow(() -> new IllegalStateException("Utilisateur non trouvé"));

        existingUser.setFirstName(updatedUser.getFirstName());
        existingUser.setLastName(updatedUser.getLastName());
        existingUser.setEmail(updatedUser.getEmail());
        existingUser.setPhone(updatedUser.getPhone());
        existingUser.setPassword(updatedUser.getPassword());
        existingUser.setRole(updatedUser.getRole());
        existingUser.setAssociation(updatedUser.getAssociation());

        return userRepository.save(existingUser);
    }

    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalStateException("Utilisateur avec l'ID " + id + " n'existe pas");
        }
        userRepository.deleteById(id);
    }

    // ✅ Trouver un utilisateur par email
    public Optional<UserAsso> findByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    // ✅ Chercher tous les utilisateurs par rôle (ex: ADMIN, MEMBER)
    public List<UserAsso> findByRole(Role role) {
        return userRepository.findByRole(role);
    }

    // ✅ Chercher les utilisateurs d'une association
    public List<UserAsso> findByAssociationId(Long associationId) {
        return userRepository.findByAssociationId(associationId);
    }


}
