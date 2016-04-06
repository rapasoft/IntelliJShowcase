package no.itera.bean;

import no.itera.model.HelloWorld;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Created by Pavol Rajzak, Itera.
 */
@Component
public class HelloWorldBean {

    @PersistenceContext
    EntityManager entityManager;

    public HelloWorld fetch(Integer id) {
        return entityManager.find(HelloWorld.class, id);
    }

    @Transactional
    public Integer create(HelloWorld helloWorld) {
        entityManager.persist(helloWorld);
        return helloWorld.getId();
    }

    public List<HelloWorld> fetchAll() {
        return entityManager.createQuery("select h from HelloWorld h", HelloWorld.class).getResultList();
    }

}
