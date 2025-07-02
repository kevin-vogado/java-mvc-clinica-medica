
package DAO;

import Model.Paciente;
import java.sql.Connection;
import java.util.ArrayList;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;

public class PacienteDAO {
    public static ArrayList<Paciente>MinhaLista = new ArrayList<Paciente>();
    public PacienteDAO(){      
    }
    public int maiorID() throws SQLException {
        int maiorID = 0;
        try{
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_pacientes");
            res.next();
            maiorID = res.getInt("id");
            
            stmt.close();
        }catch(SQLException ec){
            
        }
        return maiorID;
    
}
    public Connection getConexao(){
        Connection conexao = null;
        try{
            String driver = "com.mysql.cj.jdbc.Driver";
            Class.forName(driver);
            
            String server = "localhost";
            String database = "db_hospital"; 
            String url = "jdbc:mysql://" + server + ":3306/" + database + "?useTimezone=true&serverTimezone=UTC";
            String user = "root";
            String password = "teste"; 
            
            conexao = DriverManager.getConnection(url, user, password);
            
            if(conexao != null){
                System.out.println("Status: Conectado!");
            }else{
                System.out.println("Status: Não conectado!");
            }
            return conexao;
        }catch (ClassNotFoundException e){
            System.out.println("O driver não foi encontrado!" + e.getMessage());
            return null;
        }catch (SQLException e){
            System.out.println("Não foi possível conectar.");
            return null;
        }
    }
    public ArrayList getMinhaLista(){
        MinhaLista.clear();
        try{
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_pacientes");
            while (res.next()){
                
                String endereco = res.getString("endereco");
                String data_nasc = res.getString("data_nasc");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");
                
                Paciente paciente = new Paciente (endereco, data_nasc, id, nome, telefone);
                
                MinhaLista.add(paciente);
            }
            stmt.close();
        }catch(SQLException ex){
            
        }
        return MinhaLista;
    }
    public boolean InsertPacienteBD(Paciente obj){
        String sql = "INSERT INTO tb_pacientes(id, nome, telefone, endereco, data_nasc) VALUES (?,?,?,?,?)";
        try{
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            stmt.setInt(1, obj.getId());
            stmt.setString(2, obj.getNome());
            stmt.setString(3, obj.getTelefone());
            stmt.setString(4, obj.getEndereco());
            stmt.setString(5, obj.getData_nasc());
            
            stmt.execute();
            stmt.close();
            return true;
        }catch(SQLException erro){
            throw new RuntimeException(erro);
        }
    }
    public boolean DeletePacienteBD(int id){
        try{
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_pacientes WHERE id = " + id);
            stmt.close();
            
        }catch(SQLException erro){
            
        }
        return true;
    }
    public boolean UpdatePacienteBD(Paciente obj){
       String sql = "UPDATE tb_pacientes set nome = ?, telefone = ?, endereco = ?, data_nasc = ? WHERE id = ?";
        try{
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTelefone());
            stmt.setString(3, obj.getEndereco());
            stmt.setString(4, obj.getData_nasc());
            stmt.setInt(5, obj.getId());
            stmt.execute();
            stmt.close();
            return true;
        }catch (SQLException erro){
            throw new RuntimeException(erro);
        }
    }
    public Paciente carregaPaciente (int id){
        Paciente paci = new Paciente();
        paci.setId(id);
        try{
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * id FROM tb_pacientes WHERE id = " + id);
            res.next();
            
            paci.setNome(res.getString("nome"));
            paci.setTelefone(res.getString("telefone"));
            paci.setEndereco(res.getString("endereco"));
            paci.setData_nasc(res.getString("data_nasc"));
            
            stmt.close();
        }catch(SQLException erro){
            
        }return paci;
    }
}

