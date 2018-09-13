package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Piloto;

@SessionScoped
@ManagedBean

public class LoginControllerPiloto {
    private Piloto pilotoLogado = null;

    public LoginControllerPiloto() {
    }
    
    public String realizarLogin(String login,String senha){
        List<Piloto> pass = new PilotoController().recuperarTodos();
        
        for(Piloto p : pass){
            if(p.getLogin().equals(login)){
                if(p.getSenha().equals(senha)){
                    this.pilotoLogado = p;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("pilotoLogado", p);
                   return"menupilotologado.xhtml";
                }
            }
        }
        return "cadastrapiloto.xhtml";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        this.pilotoLogado = null;
        
        return "indexlogar.xhtml";
    }

    public Piloto getPilotoLogado() {
        return pilotoLogado;
    }

    public void setPilotoLogado(Piloto pilotoLogado) {
        this.pilotoLogado = pilotoLogado;
    }
    
}