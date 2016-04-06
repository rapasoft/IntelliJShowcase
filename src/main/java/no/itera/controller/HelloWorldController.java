package no.itera.controller;

import no.itera.bean.HelloWorldBean;
import no.itera.model.HelloWorld;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by Pavol Rajzak, Itera.
 */
@RestController
@RequestMapping(value = HelloWorldController.RESOURCE_PATH, produces = MediaType.APPLICATION_JSON_VALUE)
public class HelloWorldController {

    static final String RESOURCE_PATH = "/hello-world";

    @Autowired
    private HelloWorldBean helloWorldBean;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public List<HelloWorld> getAll() {
        return helloWorldBean.fetchAll();
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.OK)
    public HelloWorld getOne(@PathVariable Integer id) {
        return helloWorldBean.fetch(id);
    }

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    @ResponseStatus(HttpStatus.ACCEPTED)
    public Integer save(@RequestBody HelloWorld helloWorld) {
        return helloWorldBean.create(helloWorld);
    }

}
