package br.com.dicasdeumdev.api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.dicasdeumdev.api.domain.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}
