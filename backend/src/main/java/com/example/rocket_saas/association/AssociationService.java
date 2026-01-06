 package com.example.rocket_saas.association;

import com.example.rocket_saas.user.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.Objects;
import java.util.Optional;

@Service
public class AssociationService {
    private final AssociationRepository associationRepository;

    @Autowired
    public AssociationService(AssociationRepository associationRepository) {
        this.associationRepository = associationRepository;
    }

    public  Association registerNewAssociation(Association association) {
        Optional<Association> associationOptional =  associationRepository
                .findAssociationBySigle(association.getSigle().toLowerCase(Locale.ROOT));
        if(associationOptional.isPresent()) {
            throw new IllegalStateException("sigle taken");
        }
        return associationRepository.save(association);
    }


    public void deleteAssociation(Long assoId) {
        if (!associationRepository.existsById(assoId)) {
            throw new IllegalStateException("Association with ID " + assoId + " not found");
        }
        associationRepository.deleteById(assoId);
    }

    public Association updateAsso(Long assoId, String description, String sigle) {
        Association asso = associationRepository.findById(assoId)
                .orElseThrow(() -> new IllegalStateException("Association not found"));

        if (description != null && !description.isEmpty() && !Objects.equals(asso.getDescription(), description)) {
            asso.setDescription(description); // 🔧 Correction ici
        }

        if (sigle != null && !sigle.isEmpty() && !Objects.equals(asso.getSigle(), sigle)) {
            if (associationRepository.findAssociationBySigle(sigle).isPresent()) {
                throw new IllegalStateException("Sigle taken");
            }
            asso.setSigle(sigle);
        }

        return associationRepository.save(asso);
    }

    public Association findAssociationById(Long assoId) {
        return associationRepository.findById(assoId)
                .orElseThrow(() -> new EntityNotFoundException("Association with ID " + assoId + " not found"));
    }

    public List<Association> getAllAssos() {
        return associationRepository.findAll();
    }

    }
