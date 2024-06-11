import java.io.FileReader;
import java.io.IOException;

public class AccountsReader {

    //Метод вычитывает информацию по счетам и их суммам
    public void watchDataAccounts(){
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