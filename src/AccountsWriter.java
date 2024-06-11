import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;

public class AccountsWriter {
    public HashMap<String, Integer> accountsMap;

    public AccountsWriter(HashMap<String, Integer> accountsMap) {
        this.accountsMap = accountsMap;
    }

    //Метод редактирует файл со счетами и их суммами до актуального
    public void writeToAccountsFile() throws IOException {
        String oneMapKey = null;
        String oneMapValue = null;

        String[] mapCounts = accountsMap.toString().split(",");
        new FileWriter("src\\Files\\Accounts.txt", false).close();

        for (int i = 0; i < mapCounts.length; i++) {
            String[] oneMapCount = mapCounts[i].split("=");
            for (int j = 0; j < oneMapCount.length; j++) {
                oneMapCount[j] = oneMapCount[j].trim();
                oneMapKey = oneMapCount[0].replace("{", "");
                oneMapValue = oneMapCount[1].replace("}", "");
            }

            try (FileWriter stream = new FileWriter("src\\Files\\Accounts.txt", true)) {
                stream.write(oneMapKey + " | " + oneMapValue + "\n");
                stream.flush();
            } catch (IOException e) {
                System.out.println(e);
            }
        }
    }
}