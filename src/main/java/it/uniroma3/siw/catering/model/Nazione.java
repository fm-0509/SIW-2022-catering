package it.uniroma3.siw.catering.model;


import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data @NoArgsConstructor
@Entity

public class Nazione {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String codice;

}
