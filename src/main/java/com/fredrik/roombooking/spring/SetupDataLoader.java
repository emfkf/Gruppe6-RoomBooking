package com.fredrik.roombooking.spring;

import com.fredrik.roombooking.dao.PrivilegeRepository;
import com.fredrik.roombooking.dao.RoleRepository;
import com.fredrik.roombooking.dao.UserRepository;
import com.fredrik.roombooking.model.Privilege;
import com.fredrik.roombooking.model.Role;
import com.fredrik.roombooking.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

@Component
public class SetupDataLoader {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PrivilegeRepository privilegeRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /*
     After component is injected, creates read and write privileges as well as user and administrator
     privileges if they do not already exist. If no users exist, we create a user with administrator access.
     */
    @PostConstruct
    public void init() {
        final Privilege readPrivilege = createPrivilegeIfNotFound("READ_PRIVILEGE");
        final Privilege writePrivilege = createPrivilegeIfNotFound("WRITE_PRIVILEGE");

        final List<Privilege> adminPrivileges = new ArrayList<>(Arrays.asList(readPrivilege, writePrivilege));
        final List<Privilege> userPrivileges = new ArrayList<>(Collections.singletonList(readPrivilege));
        final Role adminRole = createRoleIfNotFound("ROLE_ADMIN", adminPrivileges);
        createRoleIfNotFound("ROLE_USER", userPrivileges);

        createUserIfNotFound(
                "admin",
                "admin",
                "admin",
                "password",
                new ArrayList<>(Collections.singletonList(adminRole))
        );
    }

    /*
    If the privilege does not exist in the application, save it in the database. Returns the privilege.
    */
    private Privilege createPrivilegeIfNotFound(final String name) {
        Privilege privilege = privilegeRepository.findByName(name);
        if (privilege == null) {
            privilege = new Privilege(name);
            privilege = privilegeRepository.save(privilege);
        }
        return privilege;
    }

    /*
    Saves roles if not found with the name and privileges given as parameter
    Returns the role
     */
    private Role createRoleIfNotFound(final String name, final Collection<Privilege> privileges) {
        Role role = roleRepository.findByName(name);
        if (role == null) {
            role = new Role(name);
        }
        role.setPrivileges(privileges);
        role = roleRepository.save(role);
        return role;
    }

    /*
    Creates a user if not found
     */
    private void createUserIfNotFound(final String email, final String firstName, final String lastName, final String password, final Collection<Role> roles) {
        User user = userRepository.findByEmail(email);
        if (user == null) {
            user = new User();
            user.setFirstName(firstName);
            user.setLastName(lastName);
            user.setPassword(passwordEncoder.encode(password));
            user.setEmail(email);
            user.setEnabled(true);
        }
        user.setRoles(roles);
        userRepository.save(user);
    }

}