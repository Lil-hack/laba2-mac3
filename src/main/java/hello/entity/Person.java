package hello.entity;

import com.google.common.base.MoreObjects;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "persons")
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name", length = 80)
    private String name;

    @Column(name = "age")
    private Integer age;

    @Column(name = "uid", unique = true)
    private UUID uid;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public UUID getUid() {
        return uid;
    }

    public void setUid(UUID uid) {
        this.uid = uid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        Person person = (Person) o;

        return new EqualsBuilder()
                .append(id, person.id)
                .append(name, person.name)
                .append(age, person.age)
                .append(uid, person.uid)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(id)
                .append(name)
                .append(age)
                .append(uid)
                .toHashCode();
    }

    @Override
    public String toString() {
        return MoreObjects
                .toStringHelper(this)
                .add("name", name)
                .add("age", age)
                .add("uid", uid)
                .toString();
    }
}
