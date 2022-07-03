package it.uniroma3.siw.catering.model;

import it.uniroma3.siw.catering.model.image.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data @NoArgsConstructor
@Entity
public class Piatto {

    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;

    private String descrizione;

    @ManyToMany
    private List<Ingrediente> ingredienti;

    //TODO: immagine
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

}
