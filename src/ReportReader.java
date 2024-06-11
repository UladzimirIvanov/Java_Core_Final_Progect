import java.io.FileReader;
import java.io.IOException;

public class ReportReader {

    //Метод вычитывает отчёт и выводит его в консоль
    public void watchDataReport(){
        try (FileReader stream = new FileReader("src\\Files\\Report.txt")){
            int i;
            while ((i = stream.read()) != -1){
                System.out.print((char)i);
            }
        } catch (IOException e){
            System.out.println(e);
        }
    }
}