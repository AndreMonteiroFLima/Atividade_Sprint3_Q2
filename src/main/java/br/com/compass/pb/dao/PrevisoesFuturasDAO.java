package br.com.compass.pb.dao;

import br.com.compass.pb.model.PrevisoesFuturas;
import br.com.compass.pb.util.JPAUtil;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PrevisoesFuturasDAO {
    EntityManager em;

    public PrevisoesFuturasDAO(){
        em = JPAUtil.getEntityManager();
    }

    public void cadastrar(PrevisoesFuturas previsao){
        em.persist(previsao);
    }

    public void alterar(PrevisoesFuturas previsao) {
        previsao = em.merge(previsao);
        em.persist(previsao);
    }

    public void deletar(PrevisoesFuturas previsao) {
        previsao = em.merge(previsao);
        em.remove(previsao);
    }

    public void commit() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void close(){
        em.close();
    }
}
