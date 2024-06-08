import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public HashMap<String, Integer> accountsMap;
    public ArrayList<String> fileNamesInInputFolder = new ArrayList<>();

    public FileParser(HashMap<String, Integer> accountsMap) {
        this.accountsMap = accountsMap;
    }

    //Метод просматривает каталог Input на наличие файлов и записывает их в лист
    public void readInputFilesName() {
        File folder = new File("src\\Files\\Input");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()) {
                //System.out.println(files[i].getName());
                fileNamesInInputFolder.add(String.valueOf(files[i]));
            }
        }
    }

    public HashMap<String, Integer> parsFile() {
        String accountOne;
        String accountTwo;
        int value = 0;
        boolean flag = false;
        ArrayList<String> mapKeys = new ArrayList<>(accountsMap.keySet());

        System.out.println("Мапа");
        System.out.println(accountsMap);
        System.out.println();
        for (int i = 0; i < fileNamesInInputFolder.size(); i++) {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> listWithInfFromFileInput = new ArrayList<>();

            try (FileReader stream = new FileReader(fileNamesInInputFolder.get(i))) {
                int d;
                while ((d = stream.read()) != -1) {
                    sb.append((char) d);
                }
            } catch (IOException e) {
                System.out.println(e);
            }
            Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5} \\| [0-9]{5}-[0-9]{5} \\| [0-9]*\\b");
            Matcher m = p.matcher(sb);
            while (m.find()) {
                listWithInfFromFileInput.add(m.group());
            }
            //System.out.println(listWithInfFromFileInput);

            //System.out.println("Обработака входящего файла: " + fileNamesInInputFolder.get(i).substring(15));
            for (int j = 0; j < listWithInfFromFileInput.size(); j++) {
                String oneOperation = listWithInfFromFileInput.get(j);
                String[] arrOneOperation = oneOperation.split("\\|");
                for (int k = 0; k < arrOneOperation.length; k++) {
                    arrOneOperation[k] = arrOneOperation[k].trim();
                }
                accountOne = arrOneOperation[0];
                accountTwo = arrOneOperation[1];
                value = Integer.parseInt(arrOneOperation[2]);

                for (int k = 0; k < mapKeys.size(); k++) {
                    if (accountOne.equals(mapKeys.get(k))) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }
                if (flag) {
                    for (int k = 0; k < mapKeys.size(); k++) {
                        if (accountTwo.equals(mapKeys.get(k))) {
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }
                }
                if (flag) {
                    accountsMap.replace(accountOne, accountsMap.get(accountOne) - value);
                    accountsMap.replace(accountTwo, accountsMap.get(accountTwo) + value);
                    System.out.println(" | " + fileNamesInInputFolder.get(i).substring(15) + " | перевод с " + accountOne + " на " + accountTwo + " " + value + " | успешно обработан");
                    flag = false;
                }
            }
            System.out.println();
            System.out.println("Новая Мапа");
            System.out.println(accountsMap);
            System.out.println();
        }
        return accountsMap;
    }
}