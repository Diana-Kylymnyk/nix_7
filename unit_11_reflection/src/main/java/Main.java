import parser.PropertyMapper;
import parser.PropertyParser;
import properties.Properties;

public class Main {

    public static void main(String[] args) {

        Properties appProperties = new PropertyMapper().map(Properties.class, new PropertyParser().getProperty(args[0]));

        System.out.println("Name = " + appProperties.nameConnection);
        System.out.println("Amount = " + appProperties.amountConnections);
        System.out.println("Date = " + appProperties.dateConnection);
        System.out.println("Int number = " + appProperties.intNumber);
        System.out.println("Float number = " + appProperties.floatNumber);
    }
}
