package gestion_ecole.entite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Classe {
    private int id;
    private String nom;
    private int niveau_id;
}
