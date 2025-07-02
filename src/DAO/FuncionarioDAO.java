package DAO;

import Model.Funcionario; 
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement; 

public class FuncionarioDAO {
    
    public static ArrayList<Funcionario> MinhaLista = new ArrayList<Funcionario>();

    public FuncionarioDAO() {
    }
    
    public int maiorID() throws SQLException {
        
        int maiorID = 0;
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT MAX(id) id FROM tb_funcionarios"); 
            res.next();
            maiorID = res.getInt("id"); 

            stmt.close();
            
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
                System.out.println("Status: NAO Conectado!");
            }

            return connection; 
            
        } catch (ClassNotFoundException e) { 
            System.out.println("O driver nao foi encontrado. " + e.getMessage() );
            return null;
        } catch (SQLException e) {
            System.out.println("Nao foi possivel conectar...");
            return null;
        } 
    
    } 

    public ArrayList getMinhaLista() {
        
        MinhaLista.clear();
        
            try { 
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * FROM tb_funcionarios");
            while (res.next()) {
                
                String cpf = res.getString("cpf");
                String rua = res.getString("rua");
                int numero = res.getInt("numero");
                String cep = res.getString("cep");
                String ref = res.getString("ref");
                int id = res.getInt("id");
                String nome = res.getString("nome");
                String telefone = res.getString("telefone");

                Funcionario objeto = new Funcionario(cpf, rua, numero, cep, ref, id, nome, telefone);

                MinhaLista.add(objeto);
            }

            stmt.close();

        } catch (SQLException ex) {
        }

        return MinhaLista;
        
    }
    
        
    public boolean InsertFuncionarioBD(Funcionario objeto) {
        String sql = "INSERT INTO tb_funcionarios(id, nome, telefone, cpf, rua, numero, cep, ref) VALUES(?,?,?,?,?,?,?,?)"; 

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setInt(1, objeto.getId());
            stmt.setString(2, objeto.getNome());
            stmt.setString(3, objeto.getTelefone());
            stmt.setString(4, objeto.getCpf());
            stmt.setString(5, objeto.getRua()); 
            stmt.setInt(6, objeto.getNumero());
            stmt.setString(7, objeto.getCep());
            stmt.setString(8, objeto.getRef()); 

            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        } 
    }
    
    
    public boolean DeleteFuncionarioBD(int id) {
        try {
            Statement stmt = this.getConexao().createStatement();
            stmt.executeUpdate("DELETE FROM tb_funcionarios WHERE id = " + id);
            stmt.close();            
            
        } catch (SQLException erro) {
        }
        
        return true; 
    } 

        
    public boolean UpdateFuncionarioBD(Funcionario objeto) {

        String sql = "UPDATE tb_funcionarios set nome = ? ,telefone = ? ,cpf = ? ,rua = ? ,numero = ? ,cep = ? ,ref = ? WHERE id = ?";

        try {
            PreparedStatement stmt = this.getConexao().prepareStatement(sql);

            stmt.setString(1, objeto.getNome());
            stmt.setString(2, objeto.getTelefone());
            stmt.setString(3, objeto.getCpf());
            stmt.setString(4, objeto.getRua());
            stmt.setInt(5, objeto.getNumero());
            stmt.setString(6, objeto.getCep());
            stmt.setString(7, objeto.getRef()); 
            stmt.setInt(8, objeto.getId()); 
            
            stmt.execute();
            stmt.close();

            return true;

        } catch (SQLException erro) {
            throw new RuntimeException(erro);
        }

    } 
    
        public Funcionario carregaFuncionario(int id) { 
        
        Funcionario objeto = new Funcionario(); 
        objeto.setId(id);
        
        try {
            Statement stmt = this.getConexao().createStatement();
            ResultSet res = stmt.executeQuery("SELECT * id FROM tb_funcionarios WHERE id = " + id);
            res.next();

            objeto.setNome(res.getString("nome"));
            objeto.setTelefone(res.getString("telefone"));
            objeto.setCpf(res.getString("cpf"));
            objeto.setRua(res.getString("rua")); 
            objeto.setNumero(res.getInt("numero"));
            objeto.setCep(res.getString("cep"));
            objeto.setRef(res.getString("ref"));

            stmt.close();            
            
        } catch (SQLException erro) {
        }
        return objeto; 
    }
}
    
    

