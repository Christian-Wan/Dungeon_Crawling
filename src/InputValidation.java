import java.lang.reflect.Array;

public class InputValidation {
    public InputValidation() {}

    public static boolean integerValidate(int lower, int upper, int input) {
        if (input <= upper && input >= lower) {
            return true;
        }
        return false;
    }

    public static boolean integerValidate(int lower, int upper, int outlier, int input) {
        if (input <= upper && input >= lower || input == outlier) {
            return true;
        }
        return false;
    }

    public static boolean stringValidate(String[] allowed, String input) {
        for (String allow: allowed) {
            if (input.equals(allow)) {
                return true;
            }
        }
        return false;
    }
}
