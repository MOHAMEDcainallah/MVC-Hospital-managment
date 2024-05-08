package ma.enset.patientmvc.securite.repositories;

import ma.enset.patientmvc.securite.entities.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser,String> {
    AppUser findByUsername(String username);
}
