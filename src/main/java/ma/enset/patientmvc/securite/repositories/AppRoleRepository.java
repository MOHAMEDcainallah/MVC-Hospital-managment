package ma.enset.patientmvc.securite.repositories;

import ma.enset.patientmvc.securite.entities.AppRole;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppRoleRepository extends JpaRepository<AppRole,Long> {
    AppRole findByRolename(String rolename);
}
