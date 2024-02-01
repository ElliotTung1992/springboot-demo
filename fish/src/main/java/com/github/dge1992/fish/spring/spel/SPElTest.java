package com.github.dge1992.fish.spring.spel;

import com.github.dge1992.fish.domain.Car;
import com.github.dge1992.fish.domain.Person;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.RoundingMode;
import java.util.*;

public class SPElTest {

    ExpressionParser ep = new SpelExpressionParser();

    public static void main(String[] args) {
        SPElTest test = new SPElTest();
        // test.caseOne();
        // test.caseTwo();
        // test.three();
        // test.four();
        // test.five();
        // test.six();
        // test.seven();
        // test.eight();
        // test.nine();
        test.ten();
    }

    // 类型
    private void ten() {
        RoundingMode ceiling = RoundingMode.CEILING;
        RoundingMode floor = RoundingMode.FLOOR;
        Boolean value = ep.parseExpression("T(java.math.RoundingMode).CEILING < T(java.math.RoundingMode).FLOOR").getValue(Boolean.class);
        System.out.println(value);
    }

    private void nine() {
        Boolean value = ep.parseExpression("true and false").getValue(Boolean.class);
        System.out.println(value);

        Person person = new Person();
        Boolean value1 = ep.parseExpression("isHappy('Bruce') and isHappy('Mike')").getValue(person, Boolean.class);
        System.out.println(value1);

        Boolean value2 = ep.parseExpression("true or false").getValue(Boolean.class);
        System.out.println(value2);

        Boolean value3 = ep.parseExpression("!true").getValue(Boolean.class);
        System.out.println(value3);
    }

    private void eight() {
        Boolean value = ep.parseExpression("'Bruce' instanceof T(int)").getValue(Boolean.class);
        System.out.println(value);

        boolean trueValue = ep.parseExpression(
                "'5.00' matches '^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        System.out.println(trueValue);

        //evaluates to false
        boolean falseValue = ep.parseExpression(
                "'5.0067' matches '\\^-?\\d+(\\.\\d{2})?$'").getValue(Boolean.class);
        System.out.println(falseValue);

    }

    private void seven() {
        Boolean value = ep.parseExpression("2 == 2").getValue(Boolean.class);
        System.out.println(value);

        Boolean value2 = ep.parseExpression("2 < -5").getValue(Boolean.class);
        System.out.println(value2);

        boolean trueValue = ep.parseExpression("'black' < 'block'").getValue(Boolean.class);
        System.out.println(trueValue);

    }

    private void six() {
        Map value = ep.parseExpression("{name: 'Bruce', age: 16}").getValue(Map.class);
        System.out.println(value);

        int[] arr = (int[]) ep.parseExpression("new int[4]").getValue();
        System.out.println(Arrays.toString(arr));

        int[] arr2 = (int[]) ep.parseExpression("new int[]{1,2,3}").getValue();
        System.out.println(Arrays.toString(arr2));

        String name = "Bruce";

        String value1 = ep.parseExpression("'abc'.substring(2,3)").getValue(String.class);
        System.out.println(value1);

        Person person = new Person();
        Boolean b = (Boolean) ep.parseExpression("isHappy('Mike')").getValue(person);
        System.out.println(b);
    }

    private void five() {
        ExpressionParser ep = new SpelExpressionParser();
        List list = (List) ep.parseExpression("{1,2,3,4}").getValue();
        System.out.println(list);

        List list2 = (List) ep.parseExpression("{{1,2},{3,4}}").getValue();
        System.out.println(list2);
    }

    private void four() {
        Person person = new Person();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Bruce");
        person.setMap(map);
        ExpressionParser ep = new SpelExpressionParser();
        String name = ep.parseExpression("map['name']").getValue(person, String.class);
        System.out.println(name);
    }

    private void three() {
        Person person = new Person();
        person.setName("Elliot");
        person.setAge(11);
        Car car = new Car();
        car.setCreatDate(1L);
        car.setBrand("Benz");
        List<String> tires = new ArrayList<>();
        tires.add("1");
        tires.add("2");
        tires.add("3");
        tires.add("4");
        car.setTires(tires);
        person.setCar(car);

        ExpressionParser ep = new SpelExpressionParser();
        String value = ep.parseExpression("car.tires[3]").getValue(person, String.class);
        System.out.println(value);
    }

    private void caseTwo() {
        ExpressionParser ep = new SpelExpressionParser();
        String str = (String) ep.parseExpression("'hello world'").getValue();
        System.out.println(str);

        Double d = (Double) ep.parseExpression("6.0221415E+23").getValue();
        System.out.println(d);

        Integer i = (Integer) ep.parseExpression("0x7FFFFFFF").getValue();
        System.out.println(i);

        Boolean b = (Boolean) ep.parseExpression("true").getValue();
        System.out.println(b);

        Object obj = ep.parseExpression("null").getValue();
        System.out.println(obj);

        Person person = new Person();
        person.setName("Elliot");
        person.setAge(11);
        Car car = new Car();
        car.setCreatDate(1L);
        car.setBrand("Benz");
        person.setCar(car);
        Long age = (Long) ep.parseExpression("Car.CreatDate + 20").getValue(person);
        System.out.println(age);
        String name = (String) ep.parseExpression("car.Brand").getValue(person);
        System.out.println(name);
    }

    private void caseOne() {
        ExpressionParser parser = new SpelExpressionParser();
        Expression expression = parser.parseExpression("'Hello World'.concat('!')");
        String value = (String) expression.getValue();
        System.out.println(value);

        Expression expression1 = parser.parseExpression("'Hello World'.bytes");
        byte[] bytes = (byte[]) expression1.getValue();
        System.out.println(Arrays.toString(bytes));

        Expression expression2 = parser.parseExpression("'Hello World'.bytes.length");
        System.out.println(expression2.getValue());

        Expression expression3 = parser.parseExpression("new String('HaHa').toUpperCase()");
        System.out.println(expression3.getValue(String.class));


        Person person = new Person();
        person.setName("Elliot");
        person.setAge(11);
        Expression expression4 = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(person);
        String name = (String) expression4.getValue(context);
        System.out.println(name);

        Expression expression5 = parser.parseExpression("name");
        System.out.println(expression5.getValue(person));

        Expression expression6 = parser.parseExpression("name == 'Elliot'");
        System.out.println(expression6.getValue(context, Boolean.class));
    }
}
