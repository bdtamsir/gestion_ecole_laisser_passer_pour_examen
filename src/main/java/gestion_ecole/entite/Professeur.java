package gestion_ecole.entite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Professeur {
    private int id;
    private String nom;
    private String prenom;
    private String email;
}
