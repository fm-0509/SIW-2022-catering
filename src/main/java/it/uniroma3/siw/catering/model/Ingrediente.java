package it.uniroma3.siw.catering.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data @NoArgsConstructor

@Entity

public class Ingrediente {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String origine;

    private String descrizione;

}
