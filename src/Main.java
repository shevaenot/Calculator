import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try{
            System.out.print("Введите выражение: ");
            Scanner scanner = new Scanner(System.in);
            String calcString = scanner.nextLine();

            Calc calc = new Calc();
            String result = calc.result(calcString);
            System.out.println("Ответ: " + result);
        }
        catch(CalcException | IOException e){

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}