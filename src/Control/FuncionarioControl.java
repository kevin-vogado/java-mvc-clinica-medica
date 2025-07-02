package Control;

import Model.Funcionario; 
import java.sql.SQLException;
import java.util.ArrayList;

public class FuncionarioControl {
    
    private final Funcionario funcionario;
    
    public FuncionarioControl() {
        this.funcionario = new Funcionario(); 
    }
    
    public boolean Cadastrar(String cpf, String rua, int numero, String cep, String ref, String nome, String telefone) throws SQLException {
        
        int id = funcionario.maiorID() + 1; 
        Funcionario objeto = new Funcionario(cpf, rua, numero, cep, ref, id, nome, telefone); 
        if(funcionario.InsertFuncionarioBD(objeto)){ 
            return true;
        } else {
            return false; 
        }
    } 
    
    public boolean Editar(String cpf, String rua, int numero, String cep, String ref, int id, String nome, String telefone) {
        Funcionario objeto = new Funcionario(cpf, rua, numero, cep, ref, id, nome, telefone);
        if(funcionario.UpdateFuncionarioBD(objeto)){
            return true;
        } else {
            return false;
        } 
    }
    
    public boolean Apagar(int id) {
        if(funcionario.DeleteFuncionarioBD(id)){
            return true;
        } else {
            return false;
        }
    }
    
    public Funcionario LoadFuncionario(int id) {
        return funcionario.carregaFuncionario(id); 
    }
    
    public ArrayList getMinhaLista() {
        return funcionario.getMinhaLista();
    }
    
    @SuppressWarnings("unchecked")
    public String[][] getMinhaMatrizTexto() {
        
        ArrayList<Funcionario> minhalista = funcionario.getMinhaLista();
        int tamanho = minhalista.size();
        
        String MatrizFuncionario[][] = new String[tamanho][8];
        for (int i = 0; i < tamanho; i++) {
            MatrizFuncionario[i][0] = minhalista.get(i).getId() + "";
            MatrizFuncionario[i][1] = minhalista.get(i).getNome();
            MatrizFuncionario[i][2] = minhalista.get(i).getTelefone(); 
            MatrizFuncionario[i][3] = minhalista.get(i).getCpf();
            MatrizFuncionario[i][4] = minhalista.get(i).getRua();
            MatrizFuncionario[i][5] = minhalista.get(i).getNumero() + "";
            MatrizFuncionario[i][6] = minhalista.get(i).getCep(); 
            MatrizFuncionario[i][7] = minhalista.get(i).getRef(); 
        }        
        
        return MatrizFuncionario;        
    }
    
} 
