package curry;

import java.util.function.Function;

public class CurryingExample {
    public static void main(String[] args) {

        // 1. Define the curried function.
        // It's a function that returns a function that returns a function.
        Function<String,
                Function<String,
                    Function<String, String>>> tagWrapperCurried =
                        prefix -> content -> suffix -> prefix + content + suffix;

        // 2. Use the function by applying arguments one by one.
        // Each .apply() call returns the next function in the chain.
        String html = tagWrapperCurried
                .apply("<h1>")           // This returns a Function<String, Function<String, String>>
                .apply("Hello, Currying!") // This returns a Function<String, String>
                .apply("</h1>");          // This finally returns the String result

        System.out.println(html); // Output: <h1>Hello, Currying!</h1>


        // The real power of currying: PARTIAL APPLICATION
        // You can "fix" some parameters to create more specialized functions.

        // Let's create an "h1" wrapper function by fixing the first argument.
        Function<String, Function<String, String>> h1Wrapper = tagWrapperCurried.apply("<h1>");

        // Now we can reuse this specialized function.
        String h1Title = h1Wrapper.apply("A Great Title").apply("</h1>");
        System.out.println(h1Title); // Output: <h1>A Great Title</h1>

        // Let's create an even more specialized function that wraps content in <h1> tags.
        Function<String, String> h1ContentWrapper = h1Wrapper.apply("</h1>"); // We apply the *last* argument here

        String finalContent = h1ContentWrapper.apply("Final Content");
        System.out.println(finalContent); // Output: <h1>Final Content</h1>
    }
}