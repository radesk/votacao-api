package com.dbo.api.repository.pauta;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.dbo.api.model.Pauta;
import com.dbo.api.model.Usuario;

public class PautaRepositoryImpl implements PautaRepositoryQuery{

//	@PersistenceContext
//	private EntityManager manager;
//	
//	@Override
//	public Pauta searchByName(String name) {
//		
//		CriteriaBuilder cb = manager.getCriteriaBuilder();
//		CriteriaQuery<Pauta> criteria = cb.createQuery(Pauta.class);
//		Root<Pauta> root = criteria.from(Pauta.class);
//		
//		Predicate predicates = ;
//		criteria.where(predicates);
//		
//		
//		TypedQuery<Pauta> query = manager.createQuery(criteria);
//		return query.getSingleResult();
//	}
//
////	private Predicate buildPredicates(String name, CriteriaBuilder criteriaBuilder, Root<Pauta> root) {
////			
////		return null;
////	}

}
