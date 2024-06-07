import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class AccountsReader {
    public void watchDataAccounts(){
        File file = new File("src\\Files\\Accounts.txt");
        //System.out.println(file.exists());

        try (FileReader stream = new FileReader("src\\Files\\Accounts.txt")){
            int i;
            while ((i = stream.read()) != -1){
                System.out.print((char)i);
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}
