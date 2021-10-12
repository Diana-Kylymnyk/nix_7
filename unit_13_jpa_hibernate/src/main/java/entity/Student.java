package entity;

import javax.persistence.*;

@Entity
@Table(name = "students")
public class Student extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public String getName() {
        return name;
    }
}
