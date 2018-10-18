package hello.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import hello.entity.Person;

public interface PersonRep
        extends JpaRepository<Person, Integer> { }