package controller;

import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Passageiro;

@SessionScoped
@ManagedBean

public class LoginControllerPassageiro {
    private Passageiro passageiroLogado = null;

    public LoginControllerPassageiro() {
    }
    
    public String realizarLogin(String login,String senha){
        List<Passageiro> pass = new PassageiroController().recuperarTodos();
        
        for(Passageiro p : pass){
            if(p.getLogin().equals(login)){
                if(p.getSenha().equals(senha)){
                    this.passageiroLogado = p;
                    FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("passageiroLogado", p);
                   return"menupassageirologado.xhtml";
                }
            }
        }
        return "cadastrapassageiro.xhtml";
    }
    
    public String logout(){
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        
        this.passageiroLogado = null;
        
        return "index.xhtml";
    }

    public Passageiro getPassageiroLogado() {
        return passageiroLogado;
    }

    public void setPassageiroLogado(Passageiro passageiroLogado) {
        this.passageiroLogado = passageiroLogado;
    }
    
}