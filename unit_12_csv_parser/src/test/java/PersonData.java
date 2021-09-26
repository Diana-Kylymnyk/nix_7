import csv.mapper.CSVMapping;

public class PersonData {

    @CSVMapping("name")
    private String name;

    @CSVMapping("age")
    private int age;

    @CSVMapping("gender")
    private Gender gender;

    enum Gender {MALE, FEMALE}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}