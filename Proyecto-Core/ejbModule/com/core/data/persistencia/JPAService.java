package com.core.data.persistencia;

/*
 * Autor: Stephy*/
import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

import com.core.data.entites.AbstractEntity;




public interface JPAService extends Serializable {
    
	/**
	 * Crea la entidad
	 * @param t
	 * @return
	 * @throws Exception
	 */
	<T extends AbstractEntity> T create(T t) throws Exception;
	/**
	 * Crea una serie de entidades
	 * @param col
	 * @return
	 */
	<T extends AbstractEntity> List<T> createAll(List<T> col) throws Exception;
	/**
	 * Actualiza una entidad ya persistida
	 * @param entidad
	 * @return
	 */
	<T extends AbstractEntity> T update(T entidad);
	/**
	 * Actualiza una serie de entidades ya persistidas.
	 * @param entidad
	 * @return
	 */
	<T extends AbstractEntity> List<T> updateAll(List<T> entidad);
	/**
	 * Borra una entidad
	 * @param entidad
	 */
	<T extends AbstractEntity> void delete(T entidad);
	//BUSQUEDA TUNING POR TIPO Y PARAMETROS
	
	/**
	 * Busca todas las entidades que hay
	 * @param tipoEntidad
	 * @return
	 */
    <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad);
    /**
     * Busca todas las entidades que hay con un limite de resultados
     * @param tipoEntidad
     * @param limiteDeResultados
     * @return
     */
    <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad, Integer limiteDeResultados);
    /**
     * Busca una cantidad de objetos con una serie de parametros dentro del map. Map<Campo, Valor> 
     * @param tipoEntidad
     * @param parametros
     * @return
     */
    <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros);
    /**
     * Busca una cantidad determinada de objetos con una serie de parametros dentro del map. Map<Campo, Valor> 
     * @param tipoEntidad
     * @param parametros
     * @param limiteDeResultados
     * @return
     */
    <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros, Integer limiteDeResultados);

    /**
     * Busca con una named query, devuelve una lista de resultados.
     * @param type
     * @param namedQueryName
     * @param parameters
     * @param resultLimit
     * @param lockType
     * @return
     */
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit, LockModeType... lockType);
    /**
     * Busca con una named query y una serie de parametros, devuelve una lista de resultados.
     * @param type
     * @param namedQueryName
     * @param parameters
     * @return
     */
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    /**
     * Busca con una named query, devuelve una lista de resultados.
     * @param type
     * @param namedQueryName
     * @param resultLimit
     * @param lockType
     * @return
     */
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, int resultLimit, LockModeType... lockType);
    /**
     * Busca con una named query, devuelve una lista de resultados.
     * @param type
     * @param namedQueryName
     * @return
     */
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName);
    /**
     * Busca solo un objeto con una named query
     * @param type
     * @param namedQueryName
     * @param parameters
     * @return
     */
    <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    /**
     * Busca solo un objeto con una named query
     * @param type
     * @param namedQueryName
     * @return
     */
    <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName);
    /**
     * Devuelve si el objeto existe o no a traves de una namedQuery
     * @param type
     * @param namedQueryName
     * @param parameters
     * @return
     */
    <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    /**
     * Devuelve si el objeto existe o no a traves de una namedQuery
     * @param type
     * @param namedQueryName
     * @return
     */
    <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName);
    /**
     * Borra por id
     * @param type
     * @param id
     */
    <T extends AbstractEntity, PK> void delete(Class<T> type, PK id);
    /**
     * Encuentra por id
     * @param type
     * @param id
     * @return
     */
    <T extends AbstractEntity, PK> T find(Class<T> type, PK id);
    /**
     * Devuelve si el objeto existe dado su id.
     * @param type
     * @param id
     * @return
     */
    <T extends AbstractEntity, PK> boolean exist(Class<T> type, PK id);
    /**
     * Encuentra todos los objetos de una clase determinada
     * @param type
     * @return
     */
    <T extends AbstractEntity> List<T> findAll(Class<T> type);
    /**
     * Borra todos los objetos
     * @param clazz
     */
    <T extends AbstractEntity> void deleteAll(Class<T> clazz);
    <T extends AbstractEntity, PK> void deleteAll(Class<T> clazz, List<PK> ids);
    <T extends AbstractEntity> T detach(T t);
    <T extends AbstractEntity> void unlockEntity(T t);
    <T extends AbstractEntity> long countElements(Class<T> clazz);
    <T extends AbstractEntity> long countCollectionElement(Class<T> clazz, String collectionAttribute);
    /**
     * Obtiene un elemento aleatoriamente
     * @ThreadSafe
     * @param tipoEntidad
     * @return T
     */
    <T extends AbstractEntity> T getRandomElement(Class<T> tipoEntidad);
    /**
     * Obtiene un elemento aleatoriamente tomando en cuenta los parametros
     * @ThreadSafe
     * @param tipoEntidad
     * @param parametros
     * @return T
     */
    <T extends AbstractEntity> T getRandomElementWithParameters(Class<T> tipoEntidad, Map<String, Object> parametros);
   
}