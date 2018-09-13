package controller;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Implementacoes.PilotoHibernate;
import model.Moto;
import model.Piloto;

@ManagedBean
@SessionScoped
public class PilotoController {
    private PilotoHibernate pilotoHibernate;
    private Piloto cadPiloto;
    private Piloto selectedPiloto = (Piloto) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("pilotoLogado");

    public PilotoController() {
        this.pilotoHibernate = new PilotoHibernate();
        this.cadPiloto = new Piloto();
    }
    
    public String cadastrar(Moto moto){
        this.pilotoHibernate.cadastrar(this.cadPiloto);
        this.cadPiloto.setMoto(moto);
        this.cadPiloto = new Piloto();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Piloto cadastrado com sucesso!"));
        
        return "indexlogar.xhtml";
        
    }
    
    
    public String alterar(){
        this.pilotoHibernate.alterar(this.selectedPiloto);
        
        this.cadPiloto = new Piloto();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Piloto alterado com sucesso!"));
        
        return "apresentapilotologado.xhtml";
        
    }
    
    
    public String deletar(){
        this.pilotoHibernate.deletar(this.selectedPiloto);
        
        this.cadPiloto = new Piloto();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Piloto deletado com sucesso!"));
        
        return "index.xhtml";
        
    }
    
    
    public String recuperar(){
        this.pilotoHibernate.recuperar(this.selectedPiloto.getId_piloto());
        
        this.cadPiloto = new Piloto();
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Piloto recuperado com sucesso!"));
        
        return "index.xhtml";
        
    }
    
    public List<Piloto> recuperarTodos(){
        return this.pilotoHibernate.recuperarTodos();
        
        
    }

    public Piloto getCadPiloto() {
        return cadPiloto;
    }

    public void setCadPiloto(Piloto cadPiloto) {
        this.cadPiloto = cadPiloto;
    }

    public Piloto getSelectedPiloto() {
        return selectedPiloto;
    }

    public void setSelectedPiloto(Piloto selectedPiloto) {
        this.selectedPiloto = selectedPiloto;
    }
    
    
}
