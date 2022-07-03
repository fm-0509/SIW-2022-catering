package it.uniroma3.siw.catering.repository.security;

import it.uniroma3.siw.catering.model.security.Credential;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;


public interface CredentialRepository extends CrudRepository<Credential, Long> {
	
	public Optional<Credential> findByUsername(String username);

}