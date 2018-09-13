package controller;

import java.util.List;
import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Implementacoes.CorridaHibernate;
import model.Corrida;
import model.Passageiro;
import model.Piloto;

/**
 *
 * @author Adrielly Sales
 */
@ManagedBean
@SessionScoped
public class CorridaController {

    private CorridaHibernate corridaHibernate;
    private Corrida cadCorrida;
    private Corrida selectedCorrida;

    public CorridaController() {
        this.corridaHibernate = new CorridaHibernate();
        this.cadCorrida = new Corrida();
    }

    public String cadastrar() {
        Passageiro p = (Passageiro) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get(("passageiroLogado"));
        this.cadCorrida.setPassageiro(p);
        this.corridaHibernate.cadastrar(this.cadCorrida);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Corrida cadastrada com sucesso!"));

        this.cadCorrida = new Corrida();

        return "apresentacorridaspassageiro.xhtml";

    }

    public String alterar() {
        this.corridaHibernate.alterar(this.selectedCorrida);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Corrida alterada com sucesso!"));

        this.selectedCorrida = new Corrida();

        return "apresentacorridaspassageiro.xhtml";

    }




    public void adicionaPiloto(Piloto piloto) {
        
        this.selectedCorrida.setPiloto(piloto);
        this.alterar();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Corrida alterada com sucesso!"));

        this.selectedCorrida = new Corrida();

        
    }






    public void deletar() {

        this.corridaHibernate.deletar(this.selectedCorrida);

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Corrida deletada com sucesso!"));
        this.cadCorrida = new Corrida();

    }

    public String retornar() {
        this.corridaHibernate.recuperar(this.selectedCorrida.getId());

        this.cadCorrida = new Corrida();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Corrida retornada com sucesso!"));

        return "index.xhtml";

    }

    public List<Corrida> recuperaTodos() {

        return this.corridaHibernate.recuperarTodos();

    }

    public List<Corrida> recuperarPorPiloto(Piloto piloto){
        return this.corridaHibernate.recuperarPorPiloto(piloto);
    }
            
    public List<Corrida> recuperarPorPassageiro(Passageiro passageiro){
        return this.corridaHibernate.recuperarPorPassageiro(passageiro);
    }
    
    public List<Corrida> recuperarDisponiveis(Corrida corrida){
        return this.corridaHibernate.recuperarDisponiveis(corrida);
    }
            
          
    
    
    
    
    
    
    
    
    
    
    public CorridaHibernate getCorridaHibernate() {
        return corridaHibernate;
    }

    public void setCorridaHibernate(CorridaHibernate corridaHibernate) {
        this.corridaHibernate = corridaHibernate;
    }

    public Corrida getCadCorrida() {
        return cadCorrida;
    }

    public void setCadCorrida(Corrida cadCorrida) {
        this.cadCorrida = cadCorrida;
    }

    public Corrida getSelectedCorrida() {
        return selectedCorrida;
    }

    public void setSelectedCorrida(Corrida selectedCorrida) {
        this.selectedCorrida = selectedCorrida;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 47 * hash + Objects.hashCode(this.corridaHibernate);
        hash = 47 * hash + Objects.hashCode(this.cadCorrida);
        hash = 47 * hash + Objects.hashCode(this.selectedCorrida);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final CorridaController other = (CorridaController) obj;
        if (!Objects.equals(this.corridaHibernate, other.corridaHibernate)) {
            return false;
        }
        if (!Objects.equals(this.cadCorrida, other.cadCorrida)) {
            return false;
        }
        if (!Objects.equals(this.selectedCorrida, other.selectedCorrida)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CorridaController{" + "corridaHibernate=" + corridaHibernate + ", cadCorrida=" + cadCorrida + ", selectedCorrida=" + selectedCorrida + '}';
    }

}
