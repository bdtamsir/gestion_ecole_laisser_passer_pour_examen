package gestion_ecole.repository.bd;

import gestion_ecole.core.bd.DatabaseImpl;
import gestion_ecole.entite.Cours;
import gestion_ecole.repository.CoursRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CoursRepositoryBd extends DatabaseImpl implements CoursRepository {

    @Override
    public boolean insert(Cours c) {
        int line=0;
        try {
            String nom = c.getNom().replace("'", "''");
            String module = c.getModule().replace("'", "''");
            String query = "INSERT INTO Cours (nom, module, professeur_id) VALUES ('"+nom+"','"+module+"',"+c.getProfesseur_id()+")";
            line = this.executeUpdate(query);
            ResultSet rs = statement.getGeneratedKeys();
            if(rs.next()) {
                c.setId(rs.getInt(1));
            }
        } catch(SQLException e) {
            System.out.println("Error insert cours: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return line>0;
    }

    @Override
    public List<Cours> selectAll() {
        List<Cours> coursList = new ArrayList<>();
        try {
            String query = "SELECT * FROM Cours";
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setModule(rs.getString("module"));
                c.setProfesseur_id(rs.getInt("professeur_id"));
                coursList.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error selectAll cours: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return coursList;
    }

    @Override
    public List<Cours> findByNiveauId(int niveauId) {
        List<Cours> list = new ArrayList<>();
        try {
            String query = "SELECT DISTINCT co.* FROM Cours co " +
                           "JOIN Classes_Cours cc ON co.id=cc.cours_id " +
                           "JOIN Classes cl ON cl.id=cc.classe_id " +
                           "WHERE cl.niveau_id="+niveauId;
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setModule(rs.getString("module"));
                c.setProfesseur_id(rs.getInt("professeur_id"));
                list.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error findByNiveauId: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return list;
    }

    @Override
    public List<Cours> findByClasseId(int classeId) {
        List<Cours> list = new ArrayList<>();
        try {
            String query = "SELECT co.* FROM Cours co " +
                           "JOIN Classes_Cours cc ON co.id=cc.cours_id " +
                           "WHERE cc.classe_id="+classeId;
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setModule(rs.getString("module"));
                c.setProfesseur_id(rs.getInt("professeur_id"));
                list.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error findByClasseId: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return list;
    }

    @Override
    public List<Cours> findByProfesseurId(int profId) {
        List<Cours> list = new ArrayList<>();
        try {
            String query = "SELECT * FROM Cours WHERE professeur_id="+profId;
            ResultSet rs = this.executeQuery(query);
            while(rs.next()) {
                Cours c = new Cours();
                c.setId(rs.getInt("id"));
                c.setNom(rs.getString("nom"));
                c.setModule(rs.getString("module"));
                c.setProfesseur_id(rs.getInt("professeur_id"));
                list.add(c);
            }
        } catch(SQLException e) {
            System.out.println("Error findByProfId: " + e.getMessage());
        } finally {
            this.closeConnection();
        }
        return list;
    }

    @Override
    public boolean assignCoursToClasse(int coursId, int classeId) {
    int line=0;
    try {
        String query = "INSERT INTO Classes_Cours (classe_id, cours_id) VALUES ("+classeId+","+coursId+")";
        line = this.executeUpdate(query);
    } catch(SQLException e) {
        System.out.println("Error assignCoursToClasse: " + e.getMessage());
    } finally {
        this.closeConnection();
    }
    return line>0;
}

}
