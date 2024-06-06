import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FileParser {
    public void test(){
        Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5}");
        StringBuilder sb = new StringBuilder("Hello");
        sb.append("End");
        System.out.println(sb);
        Matcher m = p.matcher("Hello i'm Daniel!"); // Передаём строку паттерна в матчер
        while (m.find()){ //.find() перегружен, можно оставить пустым или указать с какого символа искать
            System.out.println(m.group());
        }
    }
}
