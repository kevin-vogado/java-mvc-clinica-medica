package Model;

import java.util.ArrayList;
import DAO.FuncionarioDAO; 
import java.sql.SQLException; 

public class Funcionario extends Pessoa {
    
    private String cpf;
    private String rua;
    private int numero;
    private String cep;
    private String ref; 
    private final FuncionarioDAO dao; 

    public Funcionario() {
        this.dao = new FuncionarioDAO(); 
    }

    public Funcionario(String cpf, String rua, int numero, String cep, String ref) {
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.ref = ref;
        this.dao = new FuncionarioDAO(); 
    }

    public Funcionario(String cpf, String rua, int numero, String cep, String ref, int id, String nome, String telefone) {
        super(id, nome, telefone);
        this.cpf = cpf;
        this.rua = rua;
        this.numero = numero;
        this.cep = cep;
        this.ref = ref;
        this.dao = new FuncionarioDAO();  
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getRua() {
        return rua;
    }

    public void setRua(String rua) {
        this.rua = rua;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }
    
    @Override
    public String toString() {
        return "\n ID: " + this.getId() 
                + "\n Nome: " + this.getNome()
                + "\n Telefone: " + this.getTelefone()
                + "\n CPF: " + this.getCpf()
                + "\n Rua: " + this.getRua()
                + "\n Numero: " + this.getNumero()
                + "\n CEP: " + this.getCep()
                + "\n Referência: " + this.getRef()
                + "\n -----------"; 
    }
    
        
    public ArrayList getMinhaLista() {
        return dao.getMinhaLista();
    }
    
    public boolean InsertFuncionarioBD(Funcionario objeto) {
        dao.InsertFuncionarioBD(objeto);
        return true;
    } 
    
    public boolean DeleteFuncionarioBD(int id) {
        dao.DeleteFuncionarioBD(id);
        return true; 
    } 
    
    public boolean UpdateFuncionarioBD(Funcionario objeto) {
        dao.UpdateFuncionarioBD(objeto);
        return true;
    }
    
    public Funcionario carregaFuncionario(int id) {
        dao.carregaFuncionario(id);
        return null; 
    } 
    
    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
    
}
