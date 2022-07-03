package it.uniroma3.siw.catering.model;

import it.uniroma3.siw.catering.model.image.Image;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

import static it.uniroma3.siw.catering.costanti.JPA.*;

@Data @NoArgsConstructor

@Entity

public class Buffet {
    @Id @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    @ManyToOne
    private Chef chef;


    @OneToMany
    private List<Piatto> piatti;
//    private List<Piatto> piatti;

    @NotBlank
    private String nome;

    @NotBlank @Column(length = LUNGHEZZA_DESCRIZIONE)
    private String descrizione;

    //TODO: immagine
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

}
