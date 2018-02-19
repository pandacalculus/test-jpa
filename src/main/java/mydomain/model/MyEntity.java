package mydomain.model;

import javax.persistence.*;

@javax.persistence.Entity(name = "entity")
@Table(name = "entity")
public class MyEntity {

    @Id
    @Column(unique = true, updatable = false)
    public String id;

    @Version
    @Column(name = "_version")
    public Long _version;

    public static MyEntity create(String id) {
        MyEntity myEntity = new MyEntity();
        myEntity.id = id;
        return myEntity;

    }
}
