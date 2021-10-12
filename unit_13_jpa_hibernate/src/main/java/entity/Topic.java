package entity;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "topics")
public class Topic extends BasicEntity {

    @Column(nullable = false)
    private String name;

    @ManyToMany(mappedBy = "topics")
    private Set<Course> courses = new HashSet<>();

    @OneToMany(mappedBy = "topic")
    private List<Lesson> lessons = new ArrayList<>();

    public String getName() {
        return name;
    }
}