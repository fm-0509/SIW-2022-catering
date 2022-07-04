package it.uniroma3.siw.catering;

import it.uniroma3.siw.catering.model.security.Credential;
import it.uniroma3.siw.catering.service.CredentialService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static boolean IsAdmin()
    {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // in questo modo accedo all'istanza di CredentialService in maniera statica
        CredentialService credentialService = Siw2022DocenteApplication.getAppContext().getBean(CredentialService.class);
        Credential credentials = credentialService.getCredential(userDetails.getUsername());
        return credentials.getRole().equals(Credential.ADMIN_ROLE);
    }

    public static <T> List<T> IterableToList(Iterable<T> iterable)
    {
        List<T> newList = new ArrayList<>();
        for(T t : iterable)
            newList.add(t);
        return newList;
    }
}
