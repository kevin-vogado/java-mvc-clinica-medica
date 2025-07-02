package Control;

import Model.Medico;
import java.sql.SQLException;
import java.util.*;

public class MedicoControl {

    private final Medico control; 

    public MedicoControl() {
        this.control = new Medico();
    }

    public boolean Cadastrar(String nome, String crm, String especialidade, String turno, String telefone) throws SQLException {

        int id = control.maiorID() + 1;

        Medico objeto = new Medico(crm, especialidade, turno, id, nome, telefone);

        if (control.InsertMedicoDB(objeto)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean Editar(String nome, String crm, int id, String especialidade, String turno, String telefone) {

        Medico objeto1 = new Medico(crm, especialidade, turno, id, nome, telefone);

        if (control.UpdateMedicoDB(objeto1)) {
            return true;
        } else {
            return false;
        }

    }

    public boolean Deletar(int id) {

        if (control.DeleteMedicoDB(id)) {
            return true;
        } else {
            return false;
        }
    }

    public Medico CarregandoMedico(int id) {
        return control.CarregueUmMedico(id);
    }

    public ArrayList getMinhaLista() {

        return control.getMinhaLista();
    }
    
    /*aqui estamos criando um método para transformar um ArrayList de objetos para um ArrayList simples 
      (Matriz que terá linha e coluna assim como a tabela de GerenciaMedico na camada View tem linha e coluna)
    */
    //fazemos isso para que a tabela que criamos em GerenciaMedico na camada View, possa receber esses dados por linha e coluna
    @SuppressWarnings("Unchecked")
    public String[][] EntregarPraView() {

        //aqui estamos colocando a MinhaLista de DAO dentro de uma variável do tipo Medico que é um ArrayList
        //minhalista é um ArrayList de Medicos
        ArrayList<Medico> minhalista = control.getMinhaLista();
        
        //estamos tirando o tamanho da lista pelo "minhalista.size()", o size começa a contar o tamanho pelo número 1
        int tamanho = minhalista.size();
        
        /*aqui estamos criando uma Matriz que terá linha e coluna, como a lista varia de tamanho e os dados solicitados 
          continuam os mesmos, a Matriz terá na sua linha, a variável "tamanho" e na sua coluna o número 5 (0 a 4) indicando
          5 dados como: ID, Nome, Telefone, CRM, Especialidade, Turno
        */
        String Matriz[][] = new String[tamanho][6];

        //criaremos um contador FOR para contar de 0 até o número de objetos que tem dentro dessa lista 
        //por exemplo se tiver 3 objetos de Medico, o tamanho será 3 (1 a 3)
        for (int i = 0; i < tamanho; i++) {
            
            //usamos o getId + "" para somar o valor inteiro com String, assim int + String dá String
            //estamos fazendo uma concatenação entre um inteiro (getId) e uma String ("")
            /*apenas a linha i irá mudar enquanto deixamos a coluna indo de 0 a 4, pois nas colunas queremos apenas pegar
              a posição do ID, Nome, Telefone, CRM, Especialidade, Turno
              Enquanto que na linha i você vai inserindo os dados acima como ID, Nome, Telefone, CRM, Especialidade, Turno 
              em cada coluna
            */ 
            //a linha i indica a quantidade de objetos de Medico que temos para preencher nessa tabela em GerenciaMedico
            //uma matriz indica a posição (localização estilo (x,y) no plano cartesiano) de um objeto de Medico
            Matriz[i][0] = minhalista.get(i).getId() + "";
            Matriz[i][1] = minhalista.get(i).getNome();
            Matriz[i][2] = minhalista.get(i).getTelefone();
            Matriz[i][3] = minhalista.get(i).getCrm();
            Matriz[i][4] = minhalista.get(i).getEspecialidade();
            Matriz[i][5] = minhalista.get(i).getTurno();
        }
        //aqui estamos retornando para o método EntregarPraView uma Matriz com linha e coluna de objetos de Medico
        return Matriz;
    }

} 
