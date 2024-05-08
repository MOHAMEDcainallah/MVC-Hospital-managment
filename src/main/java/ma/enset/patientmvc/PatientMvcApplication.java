package ma.enset.patientmvc;

import ma.enset.patientmvc.entities.Patient;
import ma.enset.patientmvc.repositories.PatientRepository;
import ma.enset.patientmvc.securite.service.SecurityService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Date;

@SpringBootApplication
public class PatientMvcApplication {

    public static void main(String[] args) {
        SpringApplication.run(PatientMvcApplication.class, args);
    }


    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    //@Bean  // on a suffisament des donnes dans la base donc quand nous met l annotation bean on commentaire
    CommandLineRunner commandLineRunner(PatientRepository patientRepository) {
        return args -> {
            patientRepository.save(
                    new Patient(null,"Hassan",new Date(),false,122));
            patientRepository.save(
                    new Patient(null,"Mohamed",new Date(),true,321));
            patientRepository.save(
                    new Patient(null,"Yassine",new Date(),true,165));
            patientRepository.save(
                    new Patient(null,"Hanae",new Date(),false,132));

            patientRepository.findAll().forEach(p -> {
                System.out.println(p.getNom());
            });
        };
    }
    //@Bean
    CommandLineRunner saveUsers(SecurityService securityService){
        return args -> {
            securityService.saveNewUserAppUser("Rmzi","2003","2003");
            securityService.saveNewUserAppUser("Hossam","1972","1972");
            securityService.saveNewUserAppUser("Karim","2002","2002");

            securityService.saveNewRoleAppRole("ADMIN","");
            securityService.saveNewRoleAppRole("USER","");  


            securityService.addRoleToUser("Rmzi","USER");
            securityService.addRoleToUser("Rmzi","ADMIN");
            securityService.addRoleToUser("Hossam","USER");
            securityService.addRoleToUser("Karim","USER");
        };
    }
}
