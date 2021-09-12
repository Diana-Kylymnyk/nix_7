package entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import util.MyArrayList;

@Setter
@Getter
@ToString
public class Course {

    private String id;
    private String courseTitle;
    private int credit;
    private MyArrayList<String> studentListId;
}
