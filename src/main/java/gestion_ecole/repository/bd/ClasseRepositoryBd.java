package gestion_ecole.repository.bd;

import gestion_ecole.core.bd.DatabaseImpl;
import gestion_ecole.entite.Classe;
import gestion_ecole.repository.ClasseRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClasseRepositoryBd extends DatabaseImpl implements ClasseRepository {

    @Override
    public boolean insert(Classe classe) {
        int line=0;
        try {
            String nom = classe.getNom().replace("'", "''");
            String query = "INSERT INTO Classes (nom, niveau_id) VALUES ('"+nom+"',"+classe.getNiveau_id()+")";
            line = this.executeUpdate(query);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                classe.setId(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println("Error insert classe: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return line>0;
    }

    @Override
    public List<Classe> selectAll() {
        List<Classe> classes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Classes";
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Classe c = new Classe();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setNiveau_id(rs.getInt("niveau_id"));
                classes.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error selectAll classes: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return classes;
    }

    @Override
    public List<Classe> selectByNiveauId(int niveauId) {
        List<Classe> classes = new ArrayList<>();
        try {
            String query = "SELECT * FROM Classes WHERE niveau_id="+niveauId;
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Classe c = new Classe();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setNiveau_id(rs.getInt("niveau_id"));
                classes.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error selectByNiveauId classes: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return classes;
    }

    @Override
    public Classe findByName(String nom) {
        Classe c = null;
        try {
            String query = "SELECT * FROM Classes WHERE nom='"+nom.replace("'", "''")+"'";
            ResultSet rs = this.executeQuery(query);
            if(rs.next()) {
                c = new Classe();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setNiveau_id(rs.getInt("niveau_id"));
            }
        } catch(SQLException e) {
            System.out.println("Error findByName classe: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return c;
    }
}
