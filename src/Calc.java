import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Calc {

    public int num1, num2;

    private String operation;

    private int calcExp(int n1, String op, int n2) {
        int res;
        switch (op) {
            case "+":
                res = n1 + n2;
                break;
            case "-":
                res = n1-n2;
                break;
            case "*":
                res = n1*n2;
                break;
            case "/":
                res = n1/n2;
                break;
            default:
                throw new AssertionError();
        }
        return res;
    }

    public String result(String exp) throws Exception{
        boolean isRomanExp;
        Parse parse = new Parse();

        List<String> expItems = Arrays.asList(exp.split(" "));

        if (expItems.size()!=3){
            throw new CalcException("Выражение должно иметь вид: \"Число1 Операция Число2\", разделенные пробелом...");
        }

        if (parse.checkOperator(expItems.get(1))){
            operation = expItems.get(1);
        } else {
            throw new CalcException("Оператор '" + expItems.get(1) + "' не корректен, должен быть: + - * / ");
        }

        if (parse.isNumeric(expItems.get(0)) && parse.isNumeric(expItems.get(2))){
            num1 = Integer.parseInt(expItems.get(0));
            num2 = Integer.parseInt(expItems.get(2));
            isRomanExp = false;
        } else if (parse.isRoman(expItems.get(0)) && parse.isRoman(expItems.get(2))){
            num1 = parse.romeToArabConvert(expItems.get(0));
            num2 = parse.romeToArabConvert(expItems.get(2));
            isRomanExp = true;
        } else {
            throw new CalcException("Числа должны быть оба арабские или оба римские");
        }

        if (!(num1>=1 && num1<=10)){
            throw new CalcException("Число #1 должно быть от 1 до 10 или от I до X включительно");
        }

        if (!(num2>=1 && num2<=10)){
            throw new CalcException("Число #2 должно быть от 1 до 10 или от I до X включительно");
        }

        int result = calcExp(num1, operation, num2);

        if (isRomanExp){
            String sign = result < 0 ? "-" : "";
            return sign + parse.arabToRomeConvert(Math.abs(result));
        }

        return String.valueOf(result);
    }
}