package controller;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import model.Implementacoes.MotoHibernate;
import model.Moto;

@ManagedBean
@SessionScoped
public class MotoController {

    private MotoHibernate motoHibernate;
    private Moto cadMoto;
    private Moto selectedMoto;

    public MotoController() {
        this.motoHibernate = new MotoHibernate();
        this.cadMoto = new Moto();
    }

    public String cadastrar() {
        this.motoHibernate.cadastrar(this.cadMoto);

        this.cadMoto = new Moto();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moto cadastrada com sucesso!"));

        return "index.xhtml";

    }

    public String alterar() {
        this.motoHibernate.alterar(this.selectedMoto);

        this.cadMoto = new Moto();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moto alterada com sucesso!"));

        return "index.xhtml";

    }

    public String deletar() {
        this.motoHibernate.deletar(this.selectedMoto);

        this.cadMoto = new Moto();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moto deletada com sucesso!"));

        return "index.xhtml";

    }

    public String recuperar() {
        this.motoHibernate.recuperar(this.selectedMoto.getIdMoto());

        this.cadMoto = new Moto();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Moto recuperada com sucesso!"));

        return "index.xhtml";

    }

    public String recuperarTodos() {
        this.motoHibernate.recuperarTodos();

        this.cadMoto = new Moto();

        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Motos recuperadas com sucesso!"));

        return "index.xhtml";

    }

    public Moto getCadMoto() {
        return cadMoto;
    }

    public void setCadMoto(Moto cadMoto) {
        this.cadMoto = cadMoto;
    }

    public Moto getSelectedMoto() {
        return selectedMoto;
    }

    public void setSelectedMoto(Moto selectedMoto) {
        this.selectedMoto = selectedMoto;
    }

}
