package com.github.dge1992.fish.spring.spel;

import com.github.dge1992.fish.domain.Car;
import com.github.dge1992.fish.domain.po.PersonPo;
import org.springframework.expression.*;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;

import java.math.RoundingMode;
import java.util.*;

public class SPElTest {

    ExpressionParser ep = new SpelExpressionParser();

    public static void main(String[] args) throws NoSuchMethodException {
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
        // test.ten();
        // 构造
        // test.eleven();
        // 设置变量
        // test.twelve();
        // #this #root
        // test.thirteen();
        // 函数
        // test.fourteen();
        // bean引用
        // test.fifteen();
        // test.sixteen();
        // test.seventeen();
        // test.eighteen();
        // test.nineteen();

        test.demo();
    }

    private void demo() {
        PersonPo personPo = new PersonPo("Bruce");
        Car car = new Car();
        //car.setBrand("Benz");
        personPo.setCar(car);

        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setVariable("person", personPo);
        ExpressionParser parser = new SpelExpressionParser();

        String value = parser.parseExpression("#person.name").getValue(context, String.class);
        System.out.println(value);

        String value2 = parser.parseExpression("#{#person.car.Brand}", new TemplateParserContext())
                .getValue(context, String.class);
        System.out.println(value2);
    }

    private void nineteen() {
        String randomPhrase = ep.parseExpression(
                "random number is #{T(java.lang.Math).random()}",
                new TemplateParserContext()).getValue(String.class);
        System.out.println(randomPhrase);
    }

    class TemplateParserContext implements ParserContext {

        public String getExpressionPrefix() {
            return "#{";
        }

        public String getExpressionSuffix() {
            return "}";
        }

        public boolean isTemplate() {
            return true;
        }
    }

    private void eighteen() {
        PersonPo personPo = new PersonPo("Bruce");
        Car car = new Car();
        car.setBrand("Benz");
        personPo.setCar(car);
        personPo.setName("Bruce");

        StandardEvaluationContext context = new StandardEvaluationContext(personPo);
        String brand = ep.parseExpression("#person?.name").getValue(String.class);
        System.out.println(brand);
    }

    private void seventeen() {
        ExpressionParser parser = new SpelExpressionParser();
        String displayName = "name != null ? name : \"Unknown\"";
        String name = parser.parseExpression("null?:'Unknown'").getValue(String.class);

        System.out.println(name);
    }

    private void sixteen() {
        String value = ep.parseExpression("false? 'Elliot' : 'Bruce'").getValue(String.class);
        System.out.println(value);
    }

    private void fifteen() {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.setBeanResolver(new MyBeanResolver());
        Object value = ep.parseExpression("@person").getValue(context);
        System.out.println(value);
    }

    private void fourteen() throws NoSuchMethodException {
        StandardEvaluationContext context = new StandardEvaluationContext();
        context.registerFunction("isSad", PersonPo.class.getDeclaredMethod("isSad", new Class[] { String.class }));
        Boolean value = ep.parseExpression("#isSad('Bruce')").getValue(context, Boolean.class);
        System.out.println(value);
    }

    private void thirteen() {
        PersonPo personPo = new PersonPo();
        personPo.setName("Bruce");
        StandardEvaluationContext context = new StandardEvaluationContext(personPo);
        context.setVariable("name", "Bruce");
        context.setVariable("person", personPo);
        String value = ep.parseExpression("#name").getValue(context, String.class);
        System.out.println(value);
        String value1 = ep.parseExpression("#person.name").getValue(context, String.class);
        System.out.println(value1);

        // create an array of integers
        List<Integer> primes = new ArrayList<Integer>();
        primes.addAll(Arrays.asList(2,3,5,7,11,13,17));

        // create parser and set variable primes as the array of integers
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context2 = new StandardEvaluationContext();
        context2.setVariable("primes",primes);

        // all prime numbers > 10 from the list (using selection ?{...})
        // evaluates to [11, 13, 17]
        List<Integer> primesGreaterThanTen = (List<Integer>) parser.parseExpression(
                "#primes.?[#this>10]").getValue(context2);
        System.out.println(primesGreaterThanTen);
    }

    private void twelve() {
        PersonPo personPo = new PersonPo();
        StandardEvaluationContext context = new StandardEvaluationContext(personPo);
        context.setVariable("newName", "Mike Tesla");

        Object value = ep.parseExpression("name = #newName").getValue(context);
        System.out.println(value);
        System.out.println(personPo.getName());
    }

    private void eleven() {
        PersonPo personPo = ep.parseExpression("new com.github.dge1992.fish.domain.Person('Bruce')").getValue(PersonPo.class);
        System.out.println(personPo);
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

        PersonPo personPo = new PersonPo();
        Boolean value1 = ep.parseExpression("isHappy('Bruce') and isHappy('Mike')").getValue(personPo, Boolean.class);
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

        PersonPo personPo = new PersonPo();
        Boolean b = (Boolean) ep.parseExpression("isHappy('Mike')").getValue(personPo);
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
        PersonPo personPo = new PersonPo();
        Map<String, String> map = new HashMap<>();
        map.put("name", "Bruce");
        personPo.setMap(map);
        ExpressionParser ep = new SpelExpressionParser();
        String name = ep.parseExpression("map['name']").getValue(personPo, String.class);
        System.out.println(name);
    }

    private void three() {
        PersonPo personPo = new PersonPo();
        personPo.setName("Elliot");
        personPo.setAge(11);
        Car car = new Car();
        car.setCreatDate(1L);
        car.setBrand("Benz");
        List<String> tires = new ArrayList<>();
        tires.add("1");
        tires.add("2");
        tires.add("3");
        tires.add("4");
        car.setTires(tires);
        personPo.setCar(car);

        ExpressionParser ep = new SpelExpressionParser();
        String value = ep.parseExpression("car.tires[3]").getValue(personPo, String.class);
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

        PersonPo personPo = new PersonPo();
        personPo.setName("Elliot");
        personPo.setAge(11);
        Car car = new Car();
        car.setCreatDate(1L);
        car.setBrand("Benz");
        personPo.setCar(car);
        Long age = (Long) ep.parseExpression("Car.CreatDate + 20").getValue(personPo);
        System.out.println(age);
        String name = (String) ep.parseExpression("car.Brand").getValue(personPo);
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


        PersonPo personPo = new PersonPo();
        personPo.setName("Elliot");
        personPo.setAge(11);
        Expression expression4 = parser.parseExpression("name");
        EvaluationContext context = new StandardEvaluationContext(personPo);
        String name = (String) expression4.getValue(context);
        System.out.println(name);

        Expression expression5 = parser.parseExpression("name");
        System.out.println(expression5.getValue(personPo));

        Expression expression6 = parser.parseExpression("name == 'Elliot'");
        System.out.println(expression6.getValue(context, Boolean.class));
    }
}

class MyBeanResolver implements BeanResolver{

    @Override
    public Object resolve(EvaluationContext evaluationContext, String s) throws AccessException {
        return new PersonPo("Bruce");
    }
}
