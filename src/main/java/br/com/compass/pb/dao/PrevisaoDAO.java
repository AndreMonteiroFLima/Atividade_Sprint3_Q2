package br.com.compass.pb.dao;

import br.com.compass.pb.model.Previsao;
import br.com.compass.pb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrevisaoDAO {
    EntityManager em;

    public PrevisaoDAO(){
        em = JPAUtil.getEntityManager();
    }

    public void cadastrar(Previsao previsao){
        em.persist(previsao);
    }

    public void alterar(Previsao previsao) {
        previsao = em.merge(previsao);
        em.persist(previsao);
    }

    public void deletar(Previsao previsao) {
        previsao = em.merge(previsao);
        em.remove(previsao);
    }

    public Previsao verificaSeExisteRequest(String city){
        String jpql = "SELECT p FROM Previsao AS p WHERE ?1=p.data AND ?2=p.city_name";
        try {
            return em.createQuery(jpql, Previsao.class).setParameter(1, LocalDate.now()).setParameter(2, city).getSingleResult();
        }catch (NoResultException e){
            return null;
        }
    }

    public Long contaRequests(){
        String jpql = "SELECT COUNT(p) FROM Previsao AS p WHERE ?1=p.data";
        try {
            return em.createQuery(jpql,Long.class).setParameter(1,LocalDate.now()).getSingleResult();
        }catch (NoResultException e){
            return Long.valueOf("0");
        }
    }

    public void commit() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void close(){
        em.close();
    }
}
