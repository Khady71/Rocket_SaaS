package com.example.rocket_saas.association;

import com.example.rocket_saas.user.User;
import com.example.rocket_saas.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AssociationService {
    private final AssociationRepository associationRepository;

    @Autowired
    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }


}
