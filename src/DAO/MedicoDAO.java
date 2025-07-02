package DAO;

import Model.Medico;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MedicoDAO {

    public static ArrayList<Medico> MinhaLista = new ArrayList<Medico>();

    public MedicoDAO() {
    }

    public int maiorID() throws SQLException {

        int maiorID = 0;

        try {

            Statement set = this.getConexao().createStatement();

            ResultSet pega = set.executeQuery("SELECT MAX(id) id FROM tb_medicos");

            pega.next();

            maiorID = pega.getInt("id");

            set.close();

        } catch (SQLException ex) {
        }

        return maiorID;
    }

    public Connection getConexao() {

        Connection connection = null;

        try {

            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);

            String server = "localhost";
            String database = "db_hospital"; 
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "teste"; 

            connection = DriverManager.getConnection(url, user, password);

            if (connection != null) {
                System.out.println("Status: Conectado!");
            } else {
                System.out.println("Status: NÃO CONECTADO!");
            }

            return connection;

        } catch (ClassNotFoundException e) {

            System.out.println("O driver não foi encontrado");
            return null;

        } catch (SQLException e) {

            System.out.println("Não foi possível conectar !");
            return null;

        }
    }

    public ArrayList getMinhaLista() {

        MinhaLista.clear();

        try {

            Statement set = this.getConexao().createStatement();

            ResultSet pega = set.executeQuery("SELECT * FROM tb_medicos");

            while (pega.next()) {

                int id = pega.getInt("ID");

                String nome = pega.getString("Nome");

                String telefone = pega.getString("Telefone");

                String crm = pega.getString("CRM");

                String especialidade = pega.getString("Especialidade");

                String turno = pega.getString("Turno");

                Medico objeto = new Medico(crm, especialidade, turno, id, nome, telefone);

                MinhaLista.add(objeto);
            }
            set.close();
        } catch (SQLException ex) {
        }
        return MinhaLista;
    }

    public boolean InsertMedico(Medico objeto) {

        String sql = "INSERT INTO tb_medicos(ID,Nome,Telefone,CRM,Especialidade,Turno) VALUES(?,?,?,?,?,?)";

        try {

            PreparedStatement criar = this.getConexao().prepareStatement(sql);

            criar.setInt(1, objeto.getId());

            criar.setString(2, objeto.getNome());

            criar.setString(3, objeto.getTelefone());

            criar.setString(4, objeto.getCrm());

            criar.setString(5, objeto.getEspecialidade());

            criar.setString(6, objeto.getTurno());

            criar.execute();

            criar.close();

            return true;

        } catch (SQLException erro) {

            throw new RuntimeException(erro);
        }
    }

    public boolean DeleteMedico(int id) {
        try {
            Statement set = this.getConexao().createStatement();
            set.executeUpdate("DELETE FROM tb_medicos WHERE ID = " + id);

            set.close();

        } catch (SQLException erro) {
        }

        return true;
    }

    public boolean UpdateMedico(Medico objeto) {
        
        String sql = "UPDATE tb_medicos set Nome = ? ,Telefone = ? ,CRM = ? ,Especialidade = ? ,Turno = ? WHERE ID = ?";

        try {
            PreparedStatement criar = this.getConexao().prepareStatement(sql);

            criar.setString(1, objeto.getNome());

            criar.setString(2, objeto.getTelefone());

            criar.setString(3, objeto.getCrm());

            criar.setString(4, objeto.getEspecialidade());

            criar.setString(5, objeto.getTurno());

            criar.setInt(6, objeto.getId());

            criar.execute();

            criar.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    }

    public Medico CarregarMedico(int id) {

        Medico objeto = new Medico();
        objeto.setId(id);

        try {
            Statement set = this.getConexao().createStatement();

            ResultSet pega = set.executeQuery("SELECT * id FROM tb_medicos WHERE id = " + id);

            pega.next();

            objeto.setNome(pega.getString("Nome"));

            objeto.setTelefone(pega.getString("Telefone"));

            objeto.setCrm(pega.getString("CRM"));

            objeto.setEspecialidade(pega.getString("Especialidade"));

            objeto.setTurno(pega.getString("Turno"));

            set.close();

        } catch (SQLException erro) {
        }
        return objeto;
    }

} 
