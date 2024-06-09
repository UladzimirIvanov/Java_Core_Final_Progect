import java.io.FileWriter;
import java.io.IOException;

public class ReportWriter {
    public void writeToReportFile(String reportString) {
        try (FileWriter stream = new FileWriter("src\\Files\\Report.txt", true)) {
            stream.write(reportString);
            stream.flush();
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}