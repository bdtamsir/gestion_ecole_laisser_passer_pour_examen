package gestion_ecole.repository.bd;

import gestion_ecole.core.bd.DatabaseImpl;
import gestion_ecole.entite.Professeur;
import gestion_ecole.repository.ProfesseurRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesseurRepositoryBd extends DatabaseImpl implements ProfesseurRepository {

    @Override
    public boolean insert(Professeur p) {
        int line=0;
        try {
            String nom = p.getNom().replace("'", "''");
            String prenom = p.getPrenom().replace("'", "''");
            String email = p.getEmail().replace("'", "''");
            String query = "INSERT INTO Professeurs (nom, prenom, email) VALUES ('"+nom+"','"+prenom+"','"+email+"')";
            line = this.executeUpdate(query);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                p.setId(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println("Error insert professeur: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return line>0;
    }

    @Override
    public List<Professeur> selectAll() {
        List<Professeur> profs = new ArrayList<>();
        try {
            String query = "SELECT * FROM Professeurs";
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Professeur p = new Professeur();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
                profs.add(p);
            }
        } catch(SQLException e) {
            System.out.println("Error selectAll profs: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return profs;
    }

    @Override
    public Professeur findByEmail(String email) {
        Professeur p = null;
        try {
            String query = "SELECT * FROM Professeurs WHERE email='"+email.replace("'", "''")+"'";
            ResultSet rs = this.executeQuery(query);
            if(rs.next()) {
                p = new Professeur();
                p.setId(rs.getInt("id"));
                p.setNom(rs.getString("nom"));
                p.setPrenom(rs.getString("prenom"));
                p.setEmail(rs.getString("email"));
            }
        } catch(SQLException e) {
            System.out.println("Error findByEmail prof: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return p;
    }
}
