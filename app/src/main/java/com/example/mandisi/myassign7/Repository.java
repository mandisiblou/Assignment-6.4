package com.example.mandisi.myassign7;

/**
 * Created by Mandisi on 2016-04-22.
 */
import java.util.Set;

/**
 * Created by hashcode on 2016/04/09.
 */
public interface Repository<E, ID> {

    E findById(ID id);

    E save(E entity);

    E update(E entity);

    E delete(E entity);

    Set<E> findAll();

    int deleteAll();
}