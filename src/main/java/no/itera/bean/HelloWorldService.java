package no.itera.bean;

import no.itera.model.HelloWorld;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Pavol Rajzak, Itera.
 */
public interface HelloWorldService {
    HelloWorld fetch(Integer id);

    @Transactional
    Integer create(HelloWorld helloWorld);

    List<HelloWorld> fetchAll();
}
