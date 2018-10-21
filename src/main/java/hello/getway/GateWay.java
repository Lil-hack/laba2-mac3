package hello.getway;

import hello.model.PersonInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


import hello.service.PersonService;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;



@RestController
@RequestMapping("/api-gateway")
public class GateWay {



    static final String URL_API_USERS_USER= "http://localhost:8080/api-users/user";
    static final String URL_API_USERS_ALL = "http://localhost:8080/api-users/all";
    static final String URL_API_USERS_CREATE = "http://localhost:8080/api-users/create";

    @Autowired
    private PersonService personService;




    @GetMapping("/user{id}")
    public ResponseEntity<PersonInfo> findOnePersons(@RequestParam Integer id) {
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USERS_USER)
                .queryParam("id", id);
        RestTemplate restTemplate = new RestTemplate();

        // Send request with GET method and default Headers.
     PersonInfo result = restTemplate.getForObject(builder.toUriString(),PersonInfo.class);

        return new ResponseEntity<PersonInfo>(result, HttpStatus.OK);
    }

    @GetMapping("/all")
    public List<PersonInfo> findAllPersons() {
        List<PersonInfo> allPersons = new ArrayList<>();

        RestTemplate restTemplate = new RestTemplate();
        PersonInfo[] allPersonsGet = restTemplate.getForObject(URL_API_USERS_ALL, PersonInfo[].class);
        allPersons= Arrays.asList(allPersonsGet);

        return allPersons;

    }
    @GetMapping("/create{name}{age}")
    public ResponseEntity<String> createPerson(@RequestParam String name,@RequestParam Integer age) {
        try {
            UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(URL_API_USERS_CREATE)
                .queryParam("name", name).queryParam("age",age);

            RestTemplate restTemplate = new RestTemplate();
            String result = restTemplate.getForObject(builder.toUriString(),String.class);

            return new ResponseEntity(result,HttpStatus.OK);
        }catch (Exception e)
        {
            return new ResponseEntity(HttpStatus.NO_CONTENT);
        }
    }

}
