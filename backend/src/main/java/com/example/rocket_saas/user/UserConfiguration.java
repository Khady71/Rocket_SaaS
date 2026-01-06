package com.example.rocket_saas.user;

import com.example.rocket_saas.association.Association;
import com.example.rocket_saas.association.AssociationRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    private final AssociationRepository associationRepository;

    public UserConfiguration(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

  //  @Bean
//    CommandLineRunner initializeUser(UserRepository repository) {
//        return args -> {
//            Association associationcesval = associationRepository.findAssociationBySigle("CESVAL")
//                    .orElseGet(() -> associationRepository.save(
//                            new Association("CESVAL", "Some description")
//                    ));
//
//
//            UserAsso adminAssoval = new UserAsso(
//                    "John",
//                    "Doe",
//                    "kgaye809@gmail.com",
//                    "+3375544885",
//                    Role.ASSO_ADMIN ,
//                    associationcesval
//
//            );
//
//            repository.save(adminAssoVal);
//
//        };
//    }

}
