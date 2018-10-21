package hello.service;

import hello.model.PersonInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface PersonService {
    @Nonnull
    List<PersonInfo> findAllPersons();

    @Nullable
    PersonInfo findPersonById(@Nonnull Integer id);

    @Nullable
    void createPerson(@Nonnull String name, @Nonnull Integer age);

}
