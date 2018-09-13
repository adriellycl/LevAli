
package model.Implementacoes;

import model.interfaces.MotoInterface;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import model.Moto;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author Adrielly Sales
 */

public class MotoHibernate implements MotoInterface {
    
     private EntityManager em;
    private SessionFactory sessions;
    private static MotoHibernate instance =null;
    
    public static MotoHibernate getInstance(){
        if(instance == null){
            instance = new MotoHibernate();
        }
        return instance;
}
    
    public MotoHibernate(){
        Configuration cfg = new Configuration().configure();
        this.sessions = cfg.buildSessionFactory();
        
    }

    @Override
    public void cadastrar(Moto moto) {
        Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.persist(moto);
           t.commit();
       }catch(Exception cadastroMotoErro){
           System.out.println("Algo de errado não está certo ao cadastrar moto");
           t.rollback();
       }finally{
           session.close();
       }  }

    @Override
    public void alterar(Moto moto) {
       Session session = this.sessions.openSession();
       
       Transaction t = session.beginTransaction();

        try {
            session.update(moto);
            t.commit();
        } catch (Exception e) {
            t.rollback();

        } finally {
            session.close();
        }
    }

    @Override
    public Moto recuperar(int codigo) {
        Session session = this.sessions.openSession();
        
        try{
            return(Moto) session.getSession().createQuery("from Moto where codigo=" + codigo).getResultList().get(0);
        }catch(Exception recuperaMotoErro){
            System.out.println("Algo de errado não está certo ao recuperar moto");
            
        }finally{
            session.close();
        }
        return null;
    }

    @Override
    public void deletar(Moto moto) {
        Session session = this.sessions.openSession();
       Transaction t = session.beginTransaction();
       
       try{
           session.delete(moto);
           t.commit();
       }catch(Exception cadastroMotoErro){
           System.out.println("Algo de errado não está certo ao deletar passageiro");
           t.rollback();
       }finally{
           session.close();
       }
    }

    @Override
    public List<Moto> recuperarTodos() {
        Session session = this.sessions.openSession();
      List<Moto> lista = new ArrayList();      
      try{
          lista = session.createQuery("from moto").list();
      }catch(Exception listaTodasMotosErro){
          System.out.println("Algo de errado não esta certo ao listar moto");
      }finally{
          session.close();
      }
      
      return lista;  }
}