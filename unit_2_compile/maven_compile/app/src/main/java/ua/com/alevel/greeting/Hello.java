package ua.com.alevel.greeting;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.math3.distribution.NormalDistribution;

public class Hello {

    public void print() {
        System.out.println(StringUtils.upperCase("hello"));
        System.out.println("This is maven compile");

        NormalDistribution normalDistribution = new NormalDistribution(10,2);
        double randomValue = normalDistribution.sample();
        System.out.println("Your random number: " + (int) randomValue);
    }
}