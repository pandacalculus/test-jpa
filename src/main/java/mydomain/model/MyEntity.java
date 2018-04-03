package mydomain.model;

import javax.persistence.*;


@javax.persistence.Entity(name = "entity")
@Table(name = "entity")
public class MyEntity {

    @Id
    @Column
    public Integer id;

    @Column
    public String value;

    public void setValue(String value) {
        this.value = value;
    }

    public static MyEntity create(Integer id) {
        MyEntity myEntity = new MyEntity();
        myEntity.id = id;
        return myEntity;
    }
}
