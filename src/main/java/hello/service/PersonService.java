package hello.service;

import hello.model.PersonInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.List;

public interface PersonService {
    @Nonnull
    List<PersonInfo> findAllPersons();


}
