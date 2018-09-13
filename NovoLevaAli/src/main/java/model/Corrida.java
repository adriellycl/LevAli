package model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class  Corrida {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@OneToOne
@JoinColumn(name = "cod_passageiro", referencedColumnName = "id_passageiro")
private Passageiro passageiro;
@OneToOne
@JoinColumn(name = "cod_piloto",referencedColumnName = "id_piloto")
private Piloto piloto;
@Column
private String origem;
@Column
private String destino;

       
public Corrida(){
    
}

    public Corrida( Passageiro passageiro, Piloto piloto, String origem, String destino) {
        
        this.passageiro = passageiro;
        this.piloto = piloto;
        this.origem = origem;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Passageiro getPassageiro() {
        return passageiro;
    }

    public void setPassageiro(Passageiro passageiro) {
        this.passageiro = passageiro;
    }

    public Piloto getPiloto() {
        return piloto;
    }

    public void setPiloto(Piloto piloto) {
        this.piloto = piloto;
    }

    public String getOrigem() {
        return origem;
    }

    public void setOrigem(String origem) {
        this.origem = origem;
    }

    public String getDestino() {
        return destino;
    }

    public void setDestino(String destino) {
        this.destino = destino;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 19 * hash + this.id;
        hash = 19 * hash + Objects.hashCode(this.passageiro);
        hash = 19 * hash + Objects.hashCode(this.piloto);
        hash = 19 * hash + Objects.hashCode(this.origem);
        hash = 19 * hash + Objects.hashCode(this.destino);
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
        final Corrida other = (Corrida) obj;
        if (this.id != other.id) {
            return false;
        }
        if (!Objects.equals(this.origem, other.origem)) {
            return false;
        }
        if (!Objects.equals(this.destino, other.destino)) {
            return false;
        }
        if (!Objects.equals(this.passageiro, other.passageiro)) {
            return false;
        }
        if (!Objects.equals(this.piloto, other.piloto)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Corrida{" + "id=" + id + ", passageiro=" + passageiro + ", piloto=" + piloto + ", origem=" + origem + ", destino=" + destino + '}';
    }




}
