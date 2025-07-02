
package Model;

import DAO.PacienteDAO;
import java.util.ArrayList;
import java.sql.SQLException;

public class Paciente extends Pessoa{
    private String endereco;
    private String data_nasc;
    private final PacienteDAO dao;
    
    public Paciente(){
        this.dao = new PacienteDAO();
    }
    public Paciente(String endereco, String data_nasc){
        this.endereco = endereco;
        this.data_nasc = data_nasc;
        this.dao = new PacienteDAO();
    }
    public Paciente (String endereco, String data_nasc, int id, String nome, String telefone){
        super(id, nome, telefone);
        this.endereco = endereco;
        this.data_nasc = data_nasc;
        this.dao = new PacienteDAO();
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getData_nasc() {
        return data_nasc;
    }

    public void setData_nasc(String data_nasc) {
        this.data_nasc = data_nasc;
    }
    @Override
    public String toString(){
        return "\n ID: " + this.getId()
        + "\n Nome: " + this.getNome()
        + "\n Telefone: " + this.getTelefone()
        + "\n Endereço: " + this.getEndereco()
        + "\n Data de Nascimento: " + this.getData_nasc();
    }
    public ArrayList getMinhaLista(){
        return dao.getMinhaLista();
    }
    public boolean InsertPacienteBD(Paciente paciente){
        dao.InsertPacienteBD(paciente);
        return true;
    }
    public boolean DeletePacienteBD(int id){
        dao.DeletePacienteBD(id);
        return true; 
    }
    public boolean UpdatePacienteBD(Paciente paciente){
        dao.UpdatePacienteBD(paciente);
        return true;
    }
                    
    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
    public Paciente carregaPaciente(int id){
        dao.carregaPaciente(id);
        return null;
    }
    
}

