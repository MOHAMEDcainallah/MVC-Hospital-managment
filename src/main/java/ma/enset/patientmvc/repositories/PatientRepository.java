package ma.enset.patientmvc.repositories;

import ma.enset.patientmvc.entities.Patient;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PatientRepository extends JpaRepository<Patient,Long> {

    Page<Patient> findByNomContains(String kw, Pageable pageable);
}
