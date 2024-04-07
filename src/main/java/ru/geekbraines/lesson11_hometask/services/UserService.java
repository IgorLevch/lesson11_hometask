package ru.geekbraines.lesson11_hometask.services;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.Collection;



import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

import ru.geekbraines.lesson11_hometask.entities.User;
import ru.geekbraines.lesson11_hometask.repositories.UserRepository;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService{


    private final UserRepository userRepository;

    

    public Optional<User> findByUsername(String username){

            return(((UserService) userRepository).findByUsername(username));


    }



    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{

        User user = findByUsername(username).orElseThrow(()-> new UsernameNotFoundException(
            String.format("User '%s' not found", username)));

            return new org.springframework.security.core.userdetails.User(user.getUsername(),user.getPassword(),
            mapRolesToAuthorities(user.getRoles()));

    }

    private Collection<? extends GrantedAuthority> mapRolesToAuthorities(Collection<ru.geekbraines.lesson11_hometask.entities.Role> collection){

        return (collection).stream().map(role->
        new SimpleGrantedAuthority(role.getName())).collect(Collectors.toList());

    }




}
