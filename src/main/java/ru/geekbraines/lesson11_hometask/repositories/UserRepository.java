package ru.geekbraines.lesson11_hometask.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ru.geekbraines.lesson11_hometask.entities.User;

public interface UserRepository extends JpaRepository<User, Long>{

   // Optional<User> findByUsername(String username);

}
