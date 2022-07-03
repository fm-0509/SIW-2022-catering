package it.uniroma3.siw.catering.repository.security;

import it.uniroma3.siw.catering.model.security.User;
import org.springframework.data.repository.CrudRepository;


public interface UserRepository extends CrudRepository<User, Long> {

}