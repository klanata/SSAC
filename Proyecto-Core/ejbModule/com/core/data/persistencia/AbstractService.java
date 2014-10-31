package com.core.data.persistencia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.EntityExistsException;
import javax.persistence.EntityManager;
import javax.persistence.LockModeType;
import javax.persistence.Query;
import javax.persistence.TransactionRequiredException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import com.core.data.entites.AbstractEntity;



public abstract class AbstractService implements JPAService {

	private static final long serialVersionUID = 4832751190271356946L;

    @Override
    public <T extends AbstractEntity> T create(T t) throws Exception {
        try{
        	getEntityManager().persist(t);
        }catch(EntityExistsException | IllegalArgumentException | TransactionRequiredException e){
        	throw e;
        }
        return t;
    }

    @Override
    public <T extends AbstractEntity, PK> T find(Class<T> type, PK id) {
        return getEntityManager().find(type, id);
    }

    @Override
    public <T extends AbstractEntity> T update(T t) {
        return getEntityManager().merge(t);
    }

    @Override
    public <T extends AbstractEntity, PK> void delete(Class<T> type, PK id) {
        T object = this.find(type, id);
        if (object != null) {
        	getEntityManager().remove(object);
        }
    }

    @Override
    public <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName) {
        return getEntityManager().createNamedQuery(namedQueryName, type).getResultList();
    
    }

    @Override
    public <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, int resultLimit, LockModeType... lockType) {
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQueryName, type);
        query.setMaxResults(resultLimit);
        if (lockType.length > 0) {
            query.setLockMode(lockType[0]);
        }
        return query.getResultList();
    }

    @Override
    public <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        return findWithNamedQuery(type, namedQueryName, parameters, 0);
    }

    @Override
    public <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit, LockModeType... lockType) {
        Set<Entry<String, Object>> rawParameters = parameters.entrySet();
        TypedQuery<T> query = getEntityManager().createNamedQuery(namedQueryName, type);
        if (resultLimit > 0) {
            query.setMaxResults(resultLimit);
        }
        for (Entry<String, Object> entry : rawParameters) {
            query.setParameter(entry.getKey(), entry.getValue());
        }
        if (lockType.length > 0) {
            query.setLockMode(lockType[0]);
        }
        return query.getResultList();
    }

    @Override
    public <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        List<T> res = findWithNamedQuery(type, namedQueryName, parameters);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName) {
        List<T> res = findWithNamedQuery(type, namedQueryName);
        if (res.size() > 0) {
            return res.get(0);
        } else {
            return null;
        }
    }

    @Override
    public <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters) {
        List<T> res = findWithNamedQuery(type, namedQueryName, parameters);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    public <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName) {
        List<T> res = findWithNamedQuery(type, namedQueryName);
        if (res.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    @SuppressWarnings("unchecked")
	@Override
    public <T extends AbstractEntity> List<T> findAll(Class<T> type) {
        CriteriaQuery<Object> cq = getEntityManager().getCriteriaBuilder().createQuery();
        cq.select(cq.from(type));
        
        List<T> listaRetorno = new ArrayList<T>();
        List<Object> lista = getEntityManager().createQuery(cq).getResultList();
        
        for(Object obj : lista){
        	listaRetorno.add((T)obj);
        }
        
        return listaRetorno;
    }

    @Override
    public <T extends AbstractEntity> void delete(T entity) {
    	getEntityManager().remove(getEntityManager().merge(entity));
    }

    @Override
    public <T extends AbstractEntity> void deleteAll(Class<T> clazz) {
        List<T> entidades = this.findAll(clazz);
        for (T entidad : entidades) {
            this.delete(entidad);
        }
    }

    @Override
    public <T extends AbstractEntity> T detach(T t) {
    	getEntityManager().detach(t);
        return t;
    }

    @Override
    public <T extends AbstractEntity, PK> boolean exist(Class<T> type, PK id) {
        return getEntityManager().find(type, id) != null;
    }

    @Override
    public <T extends AbstractEntity> void unlockEntity(T t) {
    	getEntityManager().lock(t, LockModeType.WRITE);
    }

    @Override
    public <T extends AbstractEntity> long countElements(Class<T> clazz) {
        CriteriaBuilder builder = getEntityManager().getCriteriaBuilder();
        CriteriaQuery<Long> cq = builder.createQuery(Long.class);
        cq.select(builder.count(cq.from(clazz)));
        return getEntityManager().createQuery(cq).getSingleResult();
    }
    
    @Override
    public <T extends AbstractEntity> long countCollectionElement(Class<T> clazz, String collectionAttribute){       
        StringBuilder sb = new StringBuilder("SELECT SIZE(X.");
        sb.append(collectionAttribute);
        sb.append(") FROM ");
        sb.append(clazz.getName());
        sb.append(" X");        
        return (long) getEntityManager().createNativeQuery(sb.toString(), Long.class).getSingleResult();         
    }
    
	protected abstract EntityManager getEntityManager();
	
	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad) {
		Query q = getEntityManager().createQuery(
				"SELECT e FROM " + tipoEntidad.getName() + " e");
		List<T> list = (List<T>) q.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad, Integer limiteDeResultados) {
		Query q = getEntityManager().createQuery(
				"SELECT e FROM " + tipoEntidad.getName() + " e");
		q.setMaxResults(limiteDeResultados);
		List<T> list = (List<T>) q.getResultList();
		return list;
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros) {
		
		String queryString = new String();
		Iterator<Entry<String, Object>> entries = parametros.entrySet().iterator();		
		queryString = queryString.concat("SELECT e FROM " + tipoEntidad.getName() + " e WHERE ");
		
		while(entries.hasNext()){			
			Entry<String, Object> entry = entries.next();			
			queryString = queryString.concat("e." + entry.getKey().toString() + " LIKE :" + entry.getKey().toString());
			if(entries.hasNext()){
				queryString = queryString.concat(" AND ");
			}
		}
		
		Query query = getEntityManager().createQuery(queryString);
		
		for(Entry<String, Object> entry : parametros.entrySet()){
			query.setParameter(entry.getKey().toString(), entry.getValue());
		}
		
		return (List<T>) query.getResultList();		
		
	}

	@SuppressWarnings("unchecked")
	@Override
	public <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros,
			Integer limiteDeResultados) {
		
		String queryString = new String();
		Iterator<Entry<String, Object>> entries = parametros.entrySet().iterator();		
		queryString = queryString.concat("SELECT e FROM " + tipoEntidad.getName() + " e WHERE ");
		
		while(entries.hasNext()){			
			Entry<String, Object> entry = entries.next();			
			queryString = queryString.concat("e." + entry.getKey().toString() + " LIKE :" + entry.getKey().toString());
			if(entries.hasNext()){
				queryString = queryString.concat(" AND ");
			}
		}
		
		Query query = getEntityManager().createQuery(queryString);
		
		for(Entry<String, Object> entry : parametros.entrySet()){
			query.setParameter(entry.getKey().toString(), entry.getValue());
		}
		query.setMaxResults(limiteDeResultados);
		return (List<T>) query.getResultList();
		
	}
	
}