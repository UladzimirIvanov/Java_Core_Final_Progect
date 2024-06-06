import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Main {
    public static void main(String[] args) {
        /*System.out.println("1. Вызов операции парсинга файлов перевода из input");
        System.out.println("2. Вызов операции вывода списка всех переводов из файла-отчёта");
        System.out.println("3. Информация по счетам");

        int PARS_FILE = 1;
        int REPORT_FILE = 2;
        int ACCOUNTS_FILE = 3;

        int userInput;

        while (true) {
            try {
                System.out.println("Введите цифру от 1 до 2:");
                userInput = new Scanner(System.in).nextInt();
                if (userInput >= 1 && userInput <= 3) {
                    break;
                } else {
                    System.out.println("Некорректные данные");
                    System.out.println();
                }
            } catch (InputMismatchException e) {
                System.out.println("Некорректные данные");
                System.out.println();
            }
        }

        if (userInput == PARS_FILE) {
            System.out.println("Пока не работает");
        }
        if (userInput == REPORT_FILE) {
            System.out.println("Пока не работает");
        }
        if (userInput == ACCOUNTS_FILE) {
            AccontsReader accontsReader = new AccontsReader();
            accontsReader.watchDataAccounts();
        }*/





        StringBuilder sb = new StringBuilder();
        ArrayList<String> list = new ArrayList<>();

        try (FileReader stream = new FileReader("src\\Files\\Accounts.txt")){
            int i;
            while ((i = stream.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e){
            System.out.println(e);
        }

        System.out.println(sb);

        System.out.println();

        Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5}");
        Matcher m = p.matcher(sb);
        while (m.find()){
            list.add(m.group());
        }

        System.out.println();
        System.out.println(list);
    }
}
