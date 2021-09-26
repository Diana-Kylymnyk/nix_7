import csv.elements.Table;
import csv.mapper.impl.CSVAnnotationMapper;
import csv.parser.impl.CSVFileParser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CSVAnnotationMapperTest {

    private CSVAnnotationMapper mapper;

    private CSVFileParser parser;

    @BeforeEach
    void setUp() {
        parser = new CSVFileParser();
        mapper = new CSVAnnotationMapper();
    }

    @Test
    void shouldMapTableToObjectList() throws Exception {
        Path source = Paths.get(Objects.requireNonNull(getClass().getResource("person.csv")).toURI());
        Table table = parser.parse(source);
        List<PersonData> data = mapper.map(table, PersonData.class);

        assertEquals(2, data.size());

        PersonData person1 = data.get(0);
        assertEquals("John", person1.getName());
        assertEquals(33, person1.getAge());
        assertEquals(PersonData.Gender.MALE, person1.getGender());

        PersonData person2 = data.get(1);
        assertEquals("Bill", person2.getName());
        assertEquals(28, person2.getAge());
        assertEquals(PersonData.Gender.MALE, person2.getGender());
    }
}
