import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public ArrayList<String> addRealAccountsToMap(){
        StringBuilder sb = new StringBuilder();
        ArrayList<String> listAccountsNames = new ArrayList<>();
        ArrayList<Integer> listAccountsCounts = new ArrayList<>();

        try (FileReader stream = new FileReader("src\\Files\\Accounts.txt")){
            int i;
            while ((i = stream.read()) != -1){
                sb.append((char)i);
            }
        } catch (IOException e){
            System.out.println(e);
        }

        Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5} \\| [0-9]*\\b");
        Matcher m = p.matcher(sb);
        while (m.find()){
            listAccountsNames.add(m.group());
        }

        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < listAccountsNames.size(); i++) {
            String forMap = listAccountsNames.get(i);
            String[] forMap2 = forMap.split("\\|");
            for (int j = 0; j < forMap2.length; j++) {
                forMap2[j] = forMap2[j].trim();
            }
            map.put(forMap2[0], Integer.valueOf(forMap2[1]));
        }
        System.out.println(map);
        //System.out.println(listAccountsNames);
        return listAccountsNames;

        /*Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5}");
        Matcher m = p.matcher(sb);
        while (m.find()){
            listAccountsNames.add(m.group());
        }
        System.out.println(listAccountsNames);
        return listAccountsNames;*/
    }

/*    public void readInputFilesName(){
        File folder = new File("src\\Files\\Input");
        File[] files = folder.listFiles();

        for (int i = 0; i < files.length; i++) {
            if (files[i].isFile()){
                System.out.println(files[i].getName());

            }
        }
    }

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


    }*/

}
