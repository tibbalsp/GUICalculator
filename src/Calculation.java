import java.util.ArrayList;
import java.util.Arrays;

public abstract class Calculation {

     static String[] Convert(String text) {
        String oP = "";
        String[] textArray = new String[5];
        String leftNeg = "1", rightNeg ="1";

        if (text.charAt(0) == '-') {
            leftNeg = ("-1");
            text = text.replaceFirst("-", "");
        }
        if (text.contains("+")) {
            oP = "\\+";
        } else if (text.contains("\u00F7")) {
            oP = "\u00F7";
        } else if (text.contains("*")) {
            oP= "\\*";
        } else if (text.contains("-")) {
            oP = "-";
        } else {
        }

        String[] splitText = text.split(oP , 2);
        System.out.println(splitText);
        String left = splitText[0];
        String right = splitText[1];
        if (right.contains("-")) {
            rightNeg = ("-1");
            right = right.replaceAll("-", "");
        }
        textArray[0] = left;
        textArray[1] = right;
        textArray[2] = oP;
        textArray[3] = leftNeg;
        textArray[4] = rightNeg;

        System.out.println(Arrays.toString(textArray));
        return textArray;
    }
    static String convertEquation( IConverter converter, String left, String right, String operation,int leftNeg, int rightNeg) {

        switch (operation) {
            case "\\+": return converter.ToBase((converter.ToDecimal(left)*leftNeg) +((rightNeg)*(converter.ToDecimal(right))));
            case "-": return converter.ToBase((converter.ToDecimal(left)*leftNeg) - ((rightNeg)*(converter.ToDecimal(right))));
            case "\\*": return converter.ToBase((converter.ToDecimal(left)*leftNeg) * ((rightNeg)*(converter.ToDecimal(right))));
            case "/": return converter.ToBase((converter.ToDecimal(left)*leftNeg) / ((rightNeg)*(converter.ToDecimal(right)))) + " R " + converter.ToBase(converter.ToDecimal(left) % converter.ToDecimal(right));
            default:
                throw new IllegalStateException("Unexpected value: " + operation);
        }

    }
    public Calculation(ArrayList<String> s){


    }

}
