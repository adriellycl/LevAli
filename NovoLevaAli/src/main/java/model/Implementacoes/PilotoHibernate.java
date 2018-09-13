/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.Implementacoes;

import model.interfaces.PilotoInterface;
import java.util.ArrayList;
import java.util.List;
import javax.faces.context.FacesContext;
import javax.persistence.EntityManager;
import model.Piloto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Adrielly Sales
 */
public class PilotoHibernate implements PilotoInterface {
    
  
    private EntityManager em;
    private SessionFactory sessions;
    private static PilotoHibernate instance =null;
    
    public static PilotoHibernate getInstance(){
        if(instance == null){
            instance = new PilotoHibernate();
        }
        return instance;
    }
    
    public PilotoHibernate(){
        Configuration cfg = new Configuration().configure();
        this.sessions = cfg.buildSessionFactory();
        
    }

    @Override
    public void cadastrar(Piloto piloto) {
        Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.persist(piloto);
           t.commit();
       }catch(Exception cadastroPilotoErro){
           System.out.println("Algo de errado não está certo ao cadastrar piloto");
           
           t.rollback();
       }finally{
           session.close();
       }
    } 

    @Override
    public void alterar(Piloto piloto) {
        Session session = this.sessions.openSession();
        Transaction t = session.beginTransaction();

        try {
            session.update(piloto);
            t.commit();
        } catch (Exception e) {
            t.rollback();

        } finally {
            session.close();
        }
         }

    @Override
    public Piloto recuperar(int codigo) {
      Session session = this.sessions.openSession();
        
        try{
            return(Piloto) session.getSession().createQuery("from Piloto where codigo=" + codigo).getResultList().get(0);
        }catch(Exception recuperaPilotoErro){
            System.out.println("Algo de errado não está certo ao recuperar piloto");
            
        }finally{
            session.close();
        }
        return null;
    }

    @Override
    public void deletar(Piloto piloto) {
     Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.delete(piloto);
           t.commit();
       }catch(Exception cadastroPilotoErro){
           System.out.println("Algo de errado não está certo ao deletar piloto");
           t.rollback();
       }finally{
           session.close();
       }   
    }

    @Override
    public List<Piloto> recuperarTodos() {
        Session session = this.sessions.openSession();
      List<Piloto> lista = new ArrayList();      
      try{
          lista = session.createQuery("from Piloto").list();
      }catch(Exception listaTodosPilotosErro){
          System.out.println("Algo de errado não esta certo ao listar pilotos");
      }finally{
          session.close();
      }
      
      return lista;
    } 
}



