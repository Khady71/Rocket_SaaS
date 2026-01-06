package com.example.rocket_saas.utils;


import com.example.rocket_saas.user.UserAsso;
import com.example.rocket_saas.user.Role;
import com.example.rocket_saas.user.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class SuperAdminInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Value("${SUPER_ADMIN_EMAIL}")
    private String superAdminEmail;

    @Value("${SUPER_ADMIN_PASSWORD}")
    private String superAdminPassword;

    public SuperAdminInitializer(UserRepository userRepository,
                                 PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        String runOnce = System.getenv("RUN_ONCE");
        if (!"true".equalsIgnoreCase(runOnce)) {
            return;
        }

        if(superAdminEmail == null || superAdminPassword == null) {
            System.out.println("SUPER_ADMIN_PASSWORD non trouvé dans les variables d'environnement.");
            return;
        }

        if (!userRepository.existsByEmail(superAdminEmail)) {
            UserAsso superAdmin = new UserAsso();
            superAdmin.setEmail(superAdminEmail);
            superAdmin.setPassword(passwordEncoder.encode(superAdminPassword));
            superAdmin.setRole(Role.SUPER_ADMIN);
            userRepository.save(superAdmin);
            System.out.println("Superadmin créé avec succès !");
        } else {
            System.out.println("Superadmin déjà présent.");
        }
    }
}
