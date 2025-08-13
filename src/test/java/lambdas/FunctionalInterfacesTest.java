package lambdas;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class FunctionalInterfacesTest {

    @SuppressWarnings({"Convert2Lambda", "Anonymous2MethodRef"})
    @Test
    public void implementConsumerUsingAnonInnerClass() throws Exception {
        Consumer<String> consumer = new Consumer<String>() {
            @Override
            public void accept(String s) {
                System.out.println(s);
            }
        };
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingLambda() throws Exception {
        Consumer<String> consumer = (String s) -> System.out.println(s);
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementConsumerUsingMethodReference() throws Exception {
        Consumer<String> consumer = System.out::println;
        consumer.accept("Hello, World!");
    }

    @Test
    public void implementSupplierUsingAnonInnerClass() throws Exception {
        var supplier = new Supplier<String>() {
            @Override
            public String get() {
                return "Hello";
            }
        };
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingLambda() throws Exception {
        Supplier<String> supplier = () -> "Hello";
        assertEquals("Hello", supplier.get());
    }

    @Test
    public void implementSupplierUsingMethodReference() throws Exception {
        Supplier<Double> supplier = new Supplier<Double>() {
            @Override
            public Double get() {
                return Math.random();
            }
        };
        // Create a Supplier<Double> that calls Math.random()
        assertTrue(supplier.get() >= 0.0);
        assertTrue(supplier.get() <= 1.0);

        // Create a DoubleSupplier that does the same
        DoubleSupplier doubleSupplier = Math::random;
        assertTrue(doubleSupplier.getAsDouble() >= 0.0);
        assertTrue(doubleSupplier.getAsDouble() <= 1.0);
    }

    @Test
    public void constructorReference() throws Exception {
        List<String> stringList = List.of("a", "b", "b", "c", "d", "d");
//        assertEquals(6, stringList.size());

        // Add the strings to a Set
//        assertEquals(4, strings.size());
//        assertEquals(HashSet.class, strings.getClass());

        // Add the strings to a TreeSet
//        assertEquals(4, sortedStrings.size());
//        assertEquals(TreeSet.class, sortedStrings.getClass());
//        assertEquals("a", sortedStrings.first());
    }

    @Test
    public void filterWithPredicate() throws Exception {
//        IntStream.of(3, 1, 4, 1, 5, 9)
//                .filter(n -> true)  // accept even nums only
//                .forEach(n -> assertTrue(n % 2 == 0));
    }
}
