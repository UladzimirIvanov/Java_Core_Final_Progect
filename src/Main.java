import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("1. Вызов операции парсинга файлов перевода из input");
        System.out.println("2. Вызов операции вывода списка всех переводов из файла-отчёта");
        System.out.println("3. Информация по счетам");

        int PARS_FILE = 1;
        int REPORT_FILE = 2;
        int ACCOUNTS_FILE = 3;

        int userInput;

        while (true) {
            try {
                System.out.println("Введите цифру от 1 до 3:");
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
            AccountsForMap accountsForMap = new AccountsForMap();
            FileParser fileParser = new FileParser(accountsForMap.addRealAccountsAndCountsToMap());

            HashMap<String,Integer> mapBackFileParser = new HashMap<>(fileParser.parsFile());

            AccountsWriter accountsWriter = new AccountsWriter(mapBackFileParser);
            accountsWriter.writeToAccountsFile();
        }
        if (userInput == REPORT_FILE) {
            System.out.println("Пока не работает");
        }
        if (userInput == ACCOUNTS_FILE) {
            AccountsReader accontsReader = new AccountsReader();
            accontsReader.watchDataAccounts();
        }




    }
}

//TODO: добавить возможность читать отчёт из программы
//TODO: исправить ошибку перемещения файлов в архив, если в архиве есть файлы с этим именем
//TODO: добавить обработку валидности файлов

