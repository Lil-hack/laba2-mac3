package hello.web;

import hello.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hello.model.PingResponse;
import hello.service.PersonService;

import java.util.List;

@RestController

public class PersonsController {

    @Autowired
    private PersonService personService;

    @GetMapping("/ping")
    public PingResponse ping() {
        return new PingResponse("ok");
    }
    @RequestMapping()
    public String getHello () {

        return "hello World!";
    }

    @RequestMapping("/{numberOne},{numberTwo}")
    public String getHello (@PathVariable int numberOne,@PathVariable int numberTwo) {
        int result=numberOne+numberTwo;

        return "hello World! Result = "+result;
    }
    @GetMapping("/lox")
    public List<PersonInfo> findAllPersons() {
        return personService.findAllPersons();
    }
}
