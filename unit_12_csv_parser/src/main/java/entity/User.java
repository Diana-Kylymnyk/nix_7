package entity;

import csv.mapper.CSVMapping;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@Setter
@ToString
public class User {

    @CSVMapping("id")
    long id;
    @CSVMapping("firstName")
    String firstName;
    @CSVMapping("middleName")
    String middleName;
    @CSVMapping("lastName")
    String lastName;
    @CSVMapping("birthDate")
    LocalDate birthDate;
    @CSVMapping("gender")
    Gender gender;
    @CSVMapping("active")
    boolean active;
    @CSVMapping("engagementScore")
    double engagementScore;
}


