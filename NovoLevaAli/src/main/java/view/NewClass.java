/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.Date;
import model.Corrida;
import model.Implementacoes.CorridaHibernate;
import model.Implementacoes.PassageiroHibernate;
import model.Passageiro;
import model.Piloto;

/**
 *
 * @author Jarvis
 */
public class NewClass {
    
    public static void main(String args[]){
       
      Corrida c = new Corrida( null, null, "origem", "destino");
        CorridaHibernate.getInstance().cadastrar(c);
    
    }
     
}
