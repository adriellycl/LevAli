package model.Implementacoes;

import model.interfaces.CorridaInterface;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Corrida;
import model.Passageiro;
import model.Piloto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Adrielly Sales
 */
public class CorridaHibernate  implements CorridaInterface{
    private EntityManager em;
    private SessionFactory sessions;
    private static CorridaHibernate instance =null;
    
    public static CorridaHibernate getInstance(){
        if(instance == null){
            instance = new CorridaHibernate();
        }
        return instance;
    }
    
    public CorridaHibernate(){
        Configuration cfg = new Configuration().configure();
        this.sessions = cfg.buildSessionFactory();
        
    }

    @Override
    public void cadastrar(Corrida corrida) {
        Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.persist(corrida);
           t.commit();
       }catch(Exception cadastroCorridaErro){
           System.out.println( cadastroCorridaErro.getCause()
                   + "\nAlgo de errado não está certo ao cadastrar corrida");
           t.rollback();
       }finally{
           session.close();
       } }

    @Override
    public void alterar(Corrida corrida) {
        Session session = this.sessions.openSession();
        Transaction t = session.beginTransaction();

        try {
            session.update(corrida);
            t.commit();
        } catch (Exception e) {
            t.rollback();

        } finally {
            session.close();
        } }

    @Override
    public Corrida recuperar(int codigo) {
         Session session = this.sessions.openSession();
        
        try{
            return(Corrida) session.getSession().createQuery("from corrida where codigo=" + codigo).getResultList().get(0);
        }catch(Exception recuperaCorridaErro){
            System.out.println("Algo de errado não está certo ao recuperar corrida");
            
        }finally{
            session.close();
        }
        return null;
    }


    @Override
    public void deletar(Corrida corrida) {
        Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.delete(corrida);
           t.commit();
       }catch(Exception cadastroCorridaErro){
           System.out.println("Algo de errado não está certo ao deletar corrida");
           t.rollback();
       }finally{
           session.close();
       } }

    @Override
    public List<Corrida> recuperarTodos() {
    Session session = this.sessions.openSession();
      List<Corrida> lista = new ArrayList();      
      try{
          lista = session.createQuery("From Corrida").list();
      }catch(Exception listaTodasCorridasErro){
          System.out.println(listaTodasCorridasErro.getCause() + "\n"+
                  "Algo de errado não esta certo ao listar corrida");
      }finally{
          session.close();
      }
      
      return lista;
    } 
    
    
    
    @Override
    public List<Corrida> recuperarPorPiloto(Piloto piloto) {
    Session session = this.sessions.openSession();
      List<Corrida> lista = new ArrayList();      
      try{
          lista = session.createQuery("From Corrida Where cod_piloto=" + piloto.getId_piloto()).list();
      }catch(Exception listaTodasCorridasErro){
          System.out.println(listaTodasCorridasErro.getCause() + "\n"+
                  "Algo de errado não esta certo ao listar corrida");
      }finally{
          session.close();
      }
      
      return lista;
    } 
    
    
    @Override
    public List<Corrida> recuperarPorPassageiro(Passageiro passageiro) {
    Session session = this.sessions.openSession();
      List<Corrida> lista = new ArrayList();      
      try{
          lista = session.createQuery("From Corrida Where cod_passageiro=" + passageiro.getId_passageiro()).list();
      }catch(Exception listaTodasCorridasErro){
          System.out.println(listaTodasCorridasErro.getCause() + "\n"+
                  "Algo de errado não esta certo ao listar corridas por passageiro");
      }finally{
          session.close();
      }
      
      return lista;
    } 
    

    @Override
    public List<Corrida> recuperarDisponiveis(Corrida corrida) {
    Session session = this.sessions.openSession();
      List<Corrida> lista = new ArrayList();      
      try{
   
          
          lista = session.createQuery("From Corrida Where cod_piloto = null").list();
          
      }catch(Exception listaTodasCorridasErro){
          System.out.println(listaTodasCorridasErro.getCause() + "\n"+
                  "Algo de errado não esta certo ao listar corridas Disponiveis");
      }finally{
          session.close();
      }
      
      return lista;
    } 






}
    
