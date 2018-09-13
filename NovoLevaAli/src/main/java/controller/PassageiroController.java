package controller;

import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Implementacoes.PassageiroHibernate;
import model.Passageiro;

/**
 *
 * @author Adrielly Sales
 */
@ManagedBean
@SessionScoped
public class PassageiroController {
    private PassageiroHibernate passageiroHibernate;
    private Passageiro cadPassageiro;
    private Passageiro selectedPassageiro = (Passageiro) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("passageiroLogado");

    public PassageiroController() {
        this.passageiroHibernate = new PassageiroHibernate();
        this.cadPassageiro = new Passageiro();
    }
    
    public String cadastrar(){
        this.passageiroHibernate.cadastrar(this.cadPassageiro);
        
        this.cadPassageiro = new Passageiro();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passageiro cadastrado com sucesso!"));
        
        return "menuPassageiro.xhtml";
        
    }

    public String alterar(){
        this.passageiroHibernate.alterar(this.selectedPassageiro);
        
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passageiro alterado com sucesso!"));
        this.cadPassageiro = new Passageiro();
        
        return "apresentapassageirologado.xhtml";
        
    }
    
     public String deletar(){
        this.passageiroHibernate.deletar(this.selectedPassageiro);
        
        this.cadPassageiro = new Passageiro();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passageiro deletado com sucesso!"));
        
        return "index.xhtml";
    
    
     }
     
      public String recuperar(){
        this.passageiroHibernate.recuperar(this.selectedPassageiro.getId_passageiro());
        
        this.cadPassageiro = new Passageiro();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Passageiro alterado com sucesso!"));
        
        return "index.xhtml";
        
      }
      
      
       public List<Passageiro> recuperarTodos(){
        return this.passageiroHibernate.recuperarTodos();
        
         
    
       }
     
    public Passageiro getCadPassageiro() {
        return cadPassageiro;
    }

    public void setCadPassageiro(Passageiro cadPassageiro) {
        this.cadPassageiro = cadPassageiro;
    }

    public Passageiro getSelectedPassageiro() {
        return selectedPassageiro;
    }

    public void setSelectedPassageiro(Passageiro selectedPassageiro) {
        this.selectedPassageiro = selectedPassageiro;
    }
    
    
}
