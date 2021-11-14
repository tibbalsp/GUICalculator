import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

interface IConverter {



    public Integer ToDecimal(String numberString);
    public String ToBase(Integer num);
}

class HexadecimalConverter implements IConverter {
    /**
     * Hexadecimal to Decimal conversion
     * @param hex
     * @return Decimal integer
     */

    public Integer ToDecimal(String hex) {
        if (hex.contains("-") == true){
            hex.replaceAll("-", "");
            return (-1)*(Integer.parseInt(hex,16));
        }
        return Integer.parseInt(hex,16);
    }

    /**
     * Decimal to Hexadecimal
     * @param num
     * @return Hexadecimal string
     */

    public String ToBase(Integer num){
        return Integer.toHexString(num);
    }

}

class BinaryConverter implements IConverter{
    /**
     * Convert Binary to Decimal
     * @param binary
     * @return Decimal value as integer
     */

    public Integer ToDecimal(String binary) { return Integer.parseInt(binary,2);
    }

    /**
     * Convert Decimal to Binary
     * @param num
     * @return Binary conversion as a string
     */

    public String ToBase(Integer num){ return Integer.toBinaryString(num); }

}

class DecimalConverter implements IConverter{


    public Integer ToDecimal(String numberString) {
        return Integer.parseInt(numberString);
    }


    public String ToBase(Integer num) {
        return Integer.toString(num);
    }
}