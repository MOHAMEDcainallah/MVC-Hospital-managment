package ma.enset.patientmvc.securite.service;

import ma.enset.patientmvc.securite.entities.AppRole;
import ma.enset.patientmvc.securite.entities.AppUser;

public interface SecurityService {
    AppUser saveNewUserAppUser(String username,String password, String rePassword);
    AppRole saveNewRoleAppRole(String rolename,String description);
    void addRoleToUser(String username , String roleName);
    void removeRoleFromUser(String username , String roleName);
    AppUser loadUserByUserName(String username);

}
