package gestion_ecole.entite;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Cours {
    private int id;
    private String nom;
    private String module;
    private int professeur_id;
    
}
