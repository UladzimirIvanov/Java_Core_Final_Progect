import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    //Метод добавляет данные существующих счетов и их значения в мапу
    public HashMap<String, Integer> addRealAccountsAndCountsToMap(){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> listAccounts = new ArrayList<>();
        HashMap<String, Integer> mapAccountsAndCounts = new HashMap<>();

        //Вычитывается файл с имеющимися счетами и суммами на них
        try (FileReader stream = new FileReader("src\\Files\\Accounts.txt")){
            int i;
            while ((i = stream.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e){
            System.out.println(e);
        }
        //По этому паттерну в лист добавляются все счета и их суммы
        Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5} \\| [0-9]*\\b");
        Matcher m = p.matcher(sb);
        while (m.find()){
            listAccounts.add(m.group());
        }
        //Перебирается лист, каждый элемент преобразуется в данные для мапы
        for (int i = 0; i < listAccounts.size(); i++) {
            String oneAccountAndCount = listAccounts.get(i);
            String[] arrOneAccountAndCount = oneAccountAndCount.split("\\|");
            for (int j = 0; j < arrOneAccountAndCount.length; j++) {
                arrOneAccountAndCount[j] = arrOneAccountAndCount[j].trim();
            }
            mapAccountsAndCounts.put(arrOneAccountAndCount[0], Integer.valueOf(arrOneAccountAndCount[1]));
        }
        System.out.println(mapAccountsAndCounts);
        return mapAccountsAndCounts;
    }

    //Метод просматривает каталог Input на наличие файлов
    public void readInputFilesName(){
        File folder = new File("src\\Files\\Input");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()){
                System.out.println(files[i].getName());
            }
        }
    }

    //Метод парсит файлы
    public void parsAndWork(){
        File folder = new File("src\\Files\\Input");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()){
                System.out.println(files[i].getName());

                StringBuilder sb = new StringBuilder();
                ArrayList<String> list = new ArrayList<>();

                try (FileReader stream = new FileReader(files[i])){
                    int d;
                    while ((d = stream.read()) != -1){
                        sb.append((char)d);
                    }
                } catch (IOException e){
                    System.out.println(e);
                }
                Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5}");
                Matcher m = p.matcher(sb);
                while (m.find()){
                    list.add(m.group());
                }
                System.out.println(list);

            }
        }


    }

}
