package ru.emergency.room.service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ru.emergency.room.entity.User;
import ru.emergency.room.repository.RoleRepository;
import ru.emergency.room.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

    @PersistenceContext
    private EntityManager em;
    @Autowired
    UserRepository userRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return user;
    }

    public boolean saveUser(User user) {
        User findByUsername = userRepository.findByUsername(user.getUsername());
        if (findByUsername != null) {
            return false;
        }
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        User save = userRepository.save(user);
        if (save == null) {
            return false;
        }
        return true;
    }

    public User loginUser(String username, String password) {
        User findByUsername = userRepository.findByUsername(username);
        if (findByUsername != null && bCryptPasswordEncoder.encode(password).equals(findByUsername.getPassword())) {
            return findByUsername;
        }
        return null;
    }
}
