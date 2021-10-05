package properties;

import annotation.PropertyKey;

import java.util.Date;

public class Properties {

    @PropertyKey(value = "properties.name")
    public String nameConnection;

    @PropertyKey(value = "properties.amount")
    public long amountConnections;

    @PropertyKey(value = "properties.date")
    public Date dateConnection;

    @PropertyKey(value = "number.int")
    public int intNumber;

    @PropertyKey(value = "number.float")
    public float floatNumber;
}
