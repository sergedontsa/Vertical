package real.prop.vertical.Tuples.User;

import real.prop.vertical.AuditModel.AuditModel;

import javax.persistence.*;


@Entity
@Table(name = "subscriber")
public class Subscriber extends AuditModel {
    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "name", nullable = false, length = 50)
    private String name;
    @Basic
    @Column(name = "email", nullable = false, length = 50)
    private String email;

    public Subscriber(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }
    public Subscriber(){}

    public Subscriber(String name, String email) {
        this.name = name;
        this.email = email;

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
