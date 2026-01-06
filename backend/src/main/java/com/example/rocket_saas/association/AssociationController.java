package com.example.rocket_saas.association;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "api/asso")
public class AssociationController {
    private final AssociationService associationService;

    @Autowired
    public AssociationController(AssociationService associationService) {
        this.associationService = associationService;
    }
    // 🔹 GET all associations
    @GetMapping
    public ResponseEntity<List<Association>> getAllAssociations() {
        return ResponseEntity.ok(associationService.getAllAssos());
    }

    // 🔹 GET association by ID
    @GetMapping("/{id}")
    public ResponseEntity<Association> getAssociationById(@PathVariable Long id) {
        return ResponseEntity.ok(associationService.findAssociationById(id));
    }

    // 🔹 POST create new association
    @PreAuthorize("hasRole('SUPER_ADMIN')")
    @PostMapping("/create")
    public ResponseEntity<Association> createAssociation(@RequestBody Association association) {
        Association saved = associationService.registerNewAssociation(association);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }

    // 🔹 PUT update association
    @PutMapping("/{id}")
    public ResponseEntity<Association> updateAssociation(
            @PathVariable Long id,
            @RequestBody Map<String, String> updates
    ) {
        String description = updates.get("description");
        String sigle = updates.get("sigle");
        Association updated = associationService.updateAsso(id, description, sigle);
        return ResponseEntity.ok(updated);
    }

    // 🔹 DELETE association
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAssociation(@PathVariable Long id) {
        associationService.deleteAssociation(id);
        return ResponseEntity.noContent().build();
    }





}
