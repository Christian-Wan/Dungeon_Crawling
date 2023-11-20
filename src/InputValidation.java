
/*
* The InputValidation class is used a bunch of static methods that are used to check if user input
* is a desired input
* */
public class InputValidation {
    public InputValidation() {}
    /*
    * Checks if the parameter input is in between the parameter lower and parameter upper
    *
    * @param lower represents the lower bound the input can be
    * @param upper represents the upper bound the input can be
    * @param input represents the users input and is being checked
    *
    * @return a boolean that indicates if the input was in the range of lower and upper
    * */
    public static boolean integerValidate(int lower, int upper, int input) {
        if (input <= upper && input >= lower) {
            return true;
        }
        return false;
    }
    /*
     * Checks if the parameter input is in between the parameter lower and parameter upper or if it matches the outlier
     *
     * @param lower represents the lower bound the input can be
     * @param upper represents the upper bound the input can be
     * @param outlier represents a value that input can be that is not inside the lower and upper bounds
     * @param input represents the users input and is being checked
     *
     * @return a boolean that indicates if the input was in the range of lower and upper or if it is the same value as outlier
     * */
    public static boolean integerValidate(int lower, int upper, int outlier, int input) {
        if (input <= upper && input >= lower || input == outlier) {
            return true;
        }
        return false;
    }
    /*
     * Checks if the parameter input is in the array of allowed Strings
     *
     * @param allowed represents the strings input is allowed to be
     * @param input represents the users input and is being checked
     *
     * @return a boolean that indicates if the input was in the array of strings or not
     * */
    public static boolean stringValidate(String[] allowed, String input) {
        for (String allow: allowed) {
            if (input.equals(allow)) {
                return true;
            }
        }
        return false;
    }
}
