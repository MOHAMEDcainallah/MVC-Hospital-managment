package ma.enset.patientmvc.securite.service;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import ma.enset.patientmvc.securite.entities.AppRole;
import ma.enset.patientmvc.securite.entities.AppUser;
import ma.enset.patientmvc.securite.repositories.AppRoleRepository;
import ma.enset.patientmvc.securite.repositories.AppUserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Slf4j
@AllArgsConstructor
@Transactional
public class SecurityServiceImpl implements SecurityService {
    private AppUserRepository appUserRepository;
    private AppRoleRepository appRoleRepository;
    private PasswordEncoder passwordEncoder;

    @Override
    public AppUser saveNewUserAppUser(String username, String password, String rePassword) {
       if (!password.equals(rePassword)) throw new RuntimeException("Password not match");
       String hashedPWD=passwordEncoder.encode(password);
       AppUser appUser=new AppUser();
       appUser.setUserId(UUID.randomUUID().toString());
       appUser.setUsername(username);
       appUser.setPassword(hashedPWD);
       appUser.setActive(true);
       AppUser savedAppUser=appUserRepository.save(appUser);
        return savedAppUser;
    }

    @Override
    public AppRole saveNewRoleAppRole(String rolename, String description) {
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if (appRole!=null) throw new RuntimeException("Role"+rolename+"Already exist");
        appRole=new AppRole();
        appRole.setRolename(rolename);
        appRole.setDescription(description);
        AppRole savedAppRole=appRoleRepository.save(appRole);
        return savedAppRole;
    }

    @Override
    public void addRoleToUser(String username, String roleName) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(roleName);
        if (appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().add(appRole);
    }

    @Override
    public void removeRoleFromUser(String username, String rolename) {
        AppUser appUser=appUserRepository.findByUsername(username);
        if (appUser==null) throw new RuntimeException("User not found");
        AppRole appRole=appRoleRepository.findByRolename(rolename);
        if (appRole==null) throw new RuntimeException("Role not found");
        appUser.getAppRoles().remove(appRole);
    }

    @Override
    public AppUser loadUserByUserName(String username) {
        return appUserRepository.findByUsername(username);
    }
}
