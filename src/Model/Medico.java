package Model;

import DAO.MedicoDAO;
import java.sql.SQLException;
import java.util.*;

public class Medico extends Pessoa {

    private String crm;
    private String especialidade;
    private String turno;
    private final MedicoDAO dao;

    public Medico() {
        this.dao = new MedicoDAO();
    }

    public Medico(String crm, String especialidade, String turno) {
        this.crm = crm;
        this.especialidade = especialidade;
        this.turno = turno;
        this.dao = new MedicoDAO();
    }

    public Medico(String crm, String especialidade, String turno, int id, String nome, String telefone) {
        super(id, nome, telefone);
        this.crm = crm;
        this.especialidade = especialidade;
        this.turno = turno;
        this.dao = new MedicoDAO();
    }

    public String getCrm() {
        return crm;
    }

    public void setCrm(String crm) {
        this.crm = crm;
    }

    public String getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(String especialidade) {
        this.especialidade = especialidade;
    }

    public String getTurno() {
        return turno;
    }

    public void setTurno(String turno) {
        this.turno = turno;
    }

    @Override
    public String toString() {
        return "\n ID: " + this.getId()
                + "\n Nome: " + this.getNome()
                + "\n Telefone: " + this.getTelefone()
                + "\n CRM: " + this.getCrm()
                + "\n Especialidade: " + this.getEspecialidade()
                + "\n Turno: " + this.getTurno()
                + "\n ------------------------";
    }

    public ArrayList getMinhaLista() {
        return dao.getMinhaLista();
    }

    public boolean InsertMedicoDB(Medico objeto) {
        dao.InsertMedico(objeto);
        return true;
    }

    public boolean DeleteMedicoDB(int id) {
//        int indice = this.procurarIndice(id);
        dao.DeleteMedico(id);
        return true;
    }

    public boolean UpdateMedicoDB(Medico objeto) {
//        int indice = this.procurarIndice(id);
//        MedicoDAO.MinhaLista.set(indice, objeto);
        dao.UpdateMedico(objeto);
        return true;
    }

    public Medico CarregueUmMedico(int id) {
        dao.CarregarMedico(id);
        return null;
    }

//    private int procurarIndice(int id) {
//
//        int indice = -1;
//        for (int i = 0; i < MedicoDAO.MinhaLista.size(); i++) {
//            if (MedicoDAO.MinhaLista.get(i).getId() == id) {
//                indice = i;
//            }
//        }
//        return indice;
//    }

    public int maiorID() throws SQLException {
        return dao.maiorID();
    }
} 
