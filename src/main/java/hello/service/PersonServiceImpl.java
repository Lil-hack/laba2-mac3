package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hello.entity.Person;
import hello.model.PersonInfo;
import hello.repository.PersonRep;

import javax.annotation.Nonnull;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PersonServiceImpl
        implements PersonService {

    @Autowired
    private PersonRep personRep;

    @Nonnull
    @Override
    @Transactional(readOnly = true)
    public List<PersonInfo> findAllPersons() {
        return personRep.findAll()
                               .stream()
                               .map(this::buildPersonInfo)
                               .collect(Collectors.toList());
    }



    @Nonnull
    private PersonInfo buildPersonInfo(Person person) {
        PersonInfo info = new PersonInfo();
        info.setAge(person.getAge());
        info.setName(person.getName());
        info.setUid(person.getUid());
        return info;
    }
}
