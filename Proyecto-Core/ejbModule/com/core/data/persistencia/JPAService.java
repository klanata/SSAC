package com.core.data.persistencia;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import javax.persistence.LockModeType;

import com.core.data.entites.AbstractEntity;




public interface JPAService extends Serializable {
    
	<T extends AbstractEntity> T create(T t) throws Exception;
	<T extends AbstractEntity> T update(T entidad);
	<T extends AbstractEntity> void delete(T entidad);
	
	//BUSQUEDA TUNING POR TIPO Y PARAMETROS
    <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad);
    <T extends AbstractEntity> List<T> findAllOf(Class<T> tipoEntidad, Integer limiteDeResultados);
    <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros);
    <T extends AbstractEntity> List<T> findByParameter(Class<T> tipoEntidad, Map<String, Object> parametros, Integer limiteDeResultados);

    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters, int resultLimit, LockModeType... lockType);
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName, int resultLimit, LockModeType... lockType);
    <T extends AbstractEntity> List<T> findWithNamedQuery(Class<T> type, String namedQueryName);
    <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    <T extends AbstractEntity> T findOneWithNamedQuery(Class<T> type, String namedQueryName);
    <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName, Map<String, Object> parameters);
    <T extends AbstractEntity> boolean existWithNamedQuery(Class<T> type, String namedQueryName);
    <T extends AbstractEntity, PK> void delete(Class<T> type, PK id);
    <T extends AbstractEntity, PK> T find(Class<T> type, PK id);
    <T extends AbstractEntity, PK> boolean exist(Class<T> type, PK id);
    <T extends AbstractEntity> List<T> findAll(Class<T> type);
    <T extends AbstractEntity> void deleteAll(Class<T> clazz);
    <T extends AbstractEntity> T detach(T t);
    <T extends AbstractEntity> void unlockEntity(T t);
    <T extends AbstractEntity> long countElements(Class<T> clazz);
    <T extends AbstractEntity> long countCollectionElement(Class<T> clazz, String collectionAttribute);
   
}