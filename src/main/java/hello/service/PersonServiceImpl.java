package hello.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import hello.entity.Person;
import hello.model.PersonInfo;
import hello.repository.PersonRep;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;
import java.util.UUID;
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



    @Nullable
    @Override
    @Transactional(readOnly = true)
    public PersonInfo findPersonById(@Nonnull Integer id) {
        return personRep.findById(id).map(this::buildPersonInfo).orElse(null);
    }

    @Nullable
    public void createPerson(@Nonnull String name, @Nonnull Integer age) {

         personRep.saveAndFlush(buildPerson(name,age));
    }


    @Nonnull
    private PersonInfo buildPersonInfo(Person person) {
        PersonInfo info = new PersonInfo();
        info.setAge(person.getAge());
        info.setName(person.getName());
        info.setUid(person.getUid());
        return info;
    }
    @Nonnull
    private Person buildPerson(@Nonnull String name, @Nonnull Integer age) {
        Person person = new Person();
        person.setAge(age);
        person.setName(name);
        person.setUid(UUID.randomUUID());
        return person;
    }
}
