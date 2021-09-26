import csv.elements.Table;
import csv.exception.CSVException;
import csv.mapper.impl.CSVAnnotationMapper;
import csv.parser.impl.CSVFileParser;
import entity.User;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class Main {

    public static void main(String[] args) throws CSVException {
        if (args[0] != null) {
            try {
                String textPath = args[0];
                Path path = Paths.get(textPath);
                CSVFileParser parser = new CSVFileParser();
                Table table = parser.parse(path);

                CSVAnnotationMapper mapper = new CSVAnnotationMapper();
                List<User> users = mapper.map(table, User.class);
                for (User user:users){
                    System.out.println(user.getFirstName()+ " " + user.getLastName());
                }
            } catch (Exception e) {
                throw new CSVException("Problem with parsing!");
            }
        }
    }
}
