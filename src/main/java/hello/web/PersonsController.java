package hello.web;

import hello.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import hello.service.PersonService;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api-users")
public class PersonsController {

    @Autowired
    private PersonService personService;


    @GetMapping()
    public ResponseEntity<String> getApi () {

        return new ResponseEntity("Hello this is API-USERS",HttpStatus.OK);
    }

    @GetMapping("/user{id}")
    public ResponseEntity<PersonInfo> personById( @RequestParam Integer id)
    {
        return  new ResponseEntity<PersonInfo>(personService.findPersonById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<PersonInfo>> findAllPersons() {

        List<PersonInfo> users = personService.findAllPersons();

        if (users.isEmpty()) {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
            // You many decide to return HttpStatus.NOT_FOUND
        }
        return new ResponseEntity<List<PersonInfo>>(users, HttpStatus.OK);

    }

    @GetMapping("/create{name}{age}")
    public ResponseEntity<String> createPerson(@RequestParam String name,@RequestParam Integer age) {
        try {
            personService.createPerson(name,age);

            return new ResponseEntity("User create",HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }
}
