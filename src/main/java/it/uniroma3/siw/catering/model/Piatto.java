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

    @ManyToOne
    private Chef chef;

    //TODO: immagine
    @OneToOne(fetch = FetchType.LAZY)
    private Image image;

    @ManyToOne
    @JoinColumn(name = "buffet_id")
    private Buffet buffet;

    public Buffet getBuffet() {
        return buffet;
    }

    public void setBuffet(Buffet buffet) {
        this.buffet = buffet;
    }
}
