import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Converter implements ConverterManager {
    private Map<Character, Integer> alphabet;

    public Converter(){
        alphabet = new HashMap<Character, Integer>();
        alphabet.put('0', 0);
        alphabet.put('1', 1);
        alphabet.put('2', 2);
        alphabet.put('3', 3);
        alphabet.put('4', 4);
        alphabet.put('5', 5);
        alphabet.put('6', 6);
        alphabet.put('7', 7);
        alphabet.put('8', 8);
        alphabet.put('9', 9);
        alphabet.put('a', 10);
        alphabet.put('b', 11);
        alphabet.put('c', 12);
        alphabet.put('d', 13);
        alphabet.put('e', 14);
        alphabet.put('f', 15);
    }

    public String convert(int P, String N, int Q) {
        if(P >= 0 && P <= 16 && Q >= 0 && Q <= 16){
            String result = "";
            int number = 0;
            if(P != 10){
                N = convertToDecimal(P, N);
            }
            result = convertToConcrete(Q, N);
            return result;
        } else {
            System.err.println("Incorrect input of the P or Q parameter!");
            return "";
        }

    }

    private String convertToDecimal(int P, String N){
        String result = "";
        int Number = 0;
        char[] chars = String.valueOf(N).toCharArray();
        for(int index = chars.length - 1, exponent = 0; index >= 0; index--, exponent++){
            if(getValue(chars[index]) >= P){
                System.err.println("Incorrect input of the data!");
                System.exit(1);
            }
            Number += (getValue(chars[index])) * Math.pow(P, exponent);
        }
        result = String.valueOf(Number);
        return result;
    }

    private String convertToConcrete(int Q, String N){
        String result = "";
        int number = Integer.parseInt(N);
        int r;
        do {
            r = number % Q;
            result += getKey(r);
            number /= Q;
        } while(number > 0);
        return reverse(result);
    }

    private String reverse(String text){
        String reversed = "";
        char[] chars = text.toCharArray();
        for(int index = chars.length - 1; index >= 0; index--){
            reversed += chars[index];
        }
        return reversed;
    }

    private char getKey(int value){
        Set<Character> keys =  alphabet.keySet();
        for(char key : keys){
            if(alphabet.get(key) == value){
                return key;
            }
        }
        return ' ';
    }

    private int getValue(char key){
        return alphabet.get(key);
    }
}
