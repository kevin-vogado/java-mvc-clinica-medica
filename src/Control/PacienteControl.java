
package Control;

import Model.Paciente; 
import java.sql.SQLException;
import java.util.ArrayList;

public class PacienteControl { 
    private final Paciente control;
    public PacienteControl(){
        this.control = new Paciente();
    }
    public boolean Cadastrar(String endereco, String data_nasc, String nome, String telefone) throws SQLException{
        int id = control.maiorID() + 1;
        System.out.println(id);
        Paciente paciente = new Paciente(endereco, data_nasc, id, nome, telefone);
        if(control.InsertPacienteBD(paciente)){
            return true;
        }else{
            return false;
        }
    }
    public boolean Editar (String endereco, String data_nasc, int id, String nome, String telefone){
        Paciente paciente = new Paciente(endereco, data_nasc, id, nome, telefone);
        if(control.UpdatePacienteBD(paciente)){
            return true;
        }else{
            return false;
        }
    }
    public boolean Apagar(int id){
        if(control.DeletePacienteBD(id)){
            return true;
        }else{
            return false;
        }
    }
    public Paciente LoadPaciente(int id){
       return control.carregaPaciente(id);
        
    }
    
    public ArrayList getMinhaLista(){
        return control.getMinhaLista();
    }
    @SuppressWarnings("unchecked")
    public String[][]getMatrizTabela(){
        ArrayList<Paciente>listaPacientes = control.getMinhaLista();
        int tamanho = listaPacientes.size();
        String TabelaPaciente[][] = new String[tamanho][5];
        for(int i = 0; i < tamanho; i++){
            TabelaPaciente[i][0] = listaPacientes.get(i).getId() + "";
            TabelaPaciente[i][1] = listaPacientes.get(i).getNome();
            TabelaPaciente[i][2] = listaPacientes.get(i).getTelefone();
            TabelaPaciente[i][3] = listaPacientes.get(i).getEndereco();
            TabelaPaciente[i][4] = listaPacientes.get(i).getData_nasc();
        }
        return TabelaPaciente;
    }
}

