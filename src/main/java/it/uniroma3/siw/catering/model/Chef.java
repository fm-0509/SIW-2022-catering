package it.uniroma3.siw.catering.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data @NoArgsConstructor
public class Chef {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String cognome;

    @ManyToOne
    private Nazione nazionalita;

}
