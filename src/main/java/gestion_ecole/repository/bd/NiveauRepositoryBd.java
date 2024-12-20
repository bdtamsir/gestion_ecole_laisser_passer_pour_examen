package gestion_ecole.repository.bd;

import gestion_ecole.core.bd.DatabaseImpl;
import gestion_ecole.entite.Niveau;
import gestion_ecole.repository.NiveauRepository;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NiveauRepositoryBd extends DatabaseImpl implements NiveauRepository {

    @Override
    public boolean insert(Niveau niveau) {
        int line=0;
        try {
            String nom = niveau.getNom().replace("'", "''");
            String query = "INSERT INTO Niveaux (nom) VALUES ('"+nom+"')";
            line = this.executeUpdate(query);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                niveau.setId(rs.getInt(1));
            }
        } catch (SQLException e) {
            System.out.println("Error insert niveau: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return line>0;
    }

    @Override
    public List<Niveau> selectAll() {
        List<Niveau> niveaux = new ArrayList<>();
        try {
            String query = "SELECT * FROM Niveaux";
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Niveau n = new Niveau();
                n.setId(rs.getInt("id"));
                n.setNom(rs.getString("nom"));
                niveaux.add(n);
            }
        } catch(SQLException e) {
            System.out.println("Error selectAll niveaux: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return niveaux;
    }

    @Override
    public Niveau findByName(String nom) {
        Niveau n = null;
        try {
            String query = "SELECT * FROM Niveaux WHERE nom='"+nom.replace("'", "''")+"'";
            ResultSet rs = this.executeQuery(query);
            if(rs.next()) {
                n = new Niveau();
                n.setId(rs.getInt("id"));
                n.setNom(rs.getString("nom"));
            }
        } catch(SQLException e) {
            System.out.println("Error findByName niveau: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return n;
    }

}
