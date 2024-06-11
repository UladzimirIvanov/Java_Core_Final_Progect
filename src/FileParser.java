import Report.ReportWriter;

import java.io.File;
import java.util.HashMap;
import java.nio.file.Path;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.format.DateTimeFormatter;

public class FileParser {
    public HashMap<String, Integer> accountsMap;
    public String reportToFile;
    ReportWriter reportWriter = new ReportWriter();
    public ArrayList<String> fileNamesInInputFolder = new ArrayList<>();
    LocalDateTime now = LocalDateTime.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
    String formatDateTime = now.format(formatter);
    DateTimeFormatter formatterForSaveToArchive = DateTimeFormatter.ofPattern("ddMMyyyy_HH_mm_ss");
    String formatDateTimeForSaveToArchive = now.format(formatterForSaveToArchive);

    public FileParser(HashMap<String, Integer> accountsMap) {
        this.accountsMap = accountsMap;
    }

    public HashMap<String, Integer> parsFile() {

        String accountOne;
        String accountTwo;
        int value;
        boolean flag = false;
        ArrayList<String> mapKeys = new ArrayList<>(accountsMap.keySet());

        //Все файлы из папки Input добавляются в массив
        File folder = new File("src\\Files\\Input\\");
        File[] files = folder.listFiles();

        //Перебераю массив, что бы найти .txt файлы, всё найденное закидываю в лист
        for (int i = 0; i < files.length; i++) {
            if (files[i].getName().endsWith(".txt")) {
                fileNamesInInputFolder.add(String.valueOf(files[i]));
            }
        }

        //Если лист пуст, запись в лог
        if (fileNamesInInputFolder.isEmpty()) {
            reportToFile = (formatDateTime + " | " + "Файл для парсинга не найден\n");
            reportWriter.writeToReportFile(reportToFile);
        }

        //Начало поочерёдной обработки файлов
        for (int i = 0; i < fileNamesInInputFolder.size(); i++) {
            StringBuilder sb = new StringBuilder();
            ArrayList<String> listWithInfFromFileInput = new ArrayList<>();

            //Вычитывание информации из файла и запись в StringBuilder
            try (FileReader stream = new FileReader(fileNamesInInputFolder.get(i))) {
                int d;
                while ((d = stream.read()) != -1) {
                    sb.append((char) d);
                }
            } catch (IOException e) {
                System.out.println(e);
            }

            //Паттерн по которому полученный StringBuilder разбивается в лист
            Pattern p = Pattern.compile("[0-9]{5}-[0-9]{5} \\| [0-9]{5}-[0-9]{5} \\| [0-9]*\\b");
            Matcher m = p.matcher(sb);
            while (m.find()) {
                listWithInfFromFileInput.add(m.group());
            }

            //Данные из листа заносятся в отдельные переменные для дальнейшей работы
            for (int j = 0; j < listWithInfFromFileInput.size(); j++) {
                String oneOperation = listWithInfFromFileInput.get(j);
                String[] arrOneOperation = oneOperation.split("\\|");
                for (int k = 0; k < arrOneOperation.length; k++) {
                    arrOneOperation[k] = arrOneOperation[k].trim();
                }
                accountOne = arrOneOperation[0];
                accountTwo = arrOneOperation[1];
                value = Integer.parseInt(arrOneOperation[2]);

                //Проверяю, есть ли полученный счёт отправителя из инпут файла в списке существующих счетов (Accounts.txt)
                for (int k = 0; k < mapKeys.size(); k++) {
                    if (accountOne.equals(mapKeys.get(k))) {
                        flag = true;
                        break;
                    } else {
                        flag = false;
                    }
                }

                //Если счёт не был найден, перевод не осуществился, записываю это в лог
                if (!flag) {
                    reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | перевод с " + accountOne + " на " + accountTwo + " " + value + " | не удался, так как счёт " + accountOne + " не найден в базе существующих счетов\n");
                    reportWriter.writeToReportFile(reportToFile);
                }

                //Если счёт отправителя был найден, проверяется, есть ли счёт получателя из инпут файла в списке существующих счетов (Accounts.txt)
                if (flag) {
                    for (int k = 0; k < mapKeys.size(); k++) {
                        if (accountTwo.equals(mapKeys.get(k))) {
                            flag = true;
                            break;
                        } else {
                            flag = false;
                        }
                    }

                    //Если счёт не был найден, перевод не осуществился, записываю это в лог
                    if (!flag) {
                        reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | перевод с " + accountOne + " на " + accountTwo + " " + value + " | не удался, так как счёт " + accountTwo + " не найден в базе существующих счетов\n");
                        reportWriter.writeToReportFile(reportToFile);
                    }
                }

                //Если оба счёта присутствуют в списке счетов, проверяю достаточно ли средств для перевода
                if (flag) {
                    //Если средств не достаточно, перевод не осуществился, записываю это в лог
                    if (accountsMap.get(accountOne) < value) {
                        reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | перевод с " + accountOne + " на " + accountTwo + " " + value + " | не удался из-за недостатка средств на счету\n");
                        reportWriter.writeToReportFile(reportToFile);
                        continue;
                    }

                    //Если средств достаточно, выполняется перевод и информация записывается в лог
                    accountsMap.replace(accountOne, accountsMap.get(accountOne) - value);
                    accountsMap.replace(accountTwo, accountsMap.get(accountTwo) + value);
                    reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | перевод с " + accountOne + " на " + accountTwo + " " + value + " | успешно обработан\n");
                    reportWriter.writeToReportFile(reportToFile);
                    flag = false;
                }
            }

            //Файлы из инпута перемещаются в архив с записю логов
            Path result = null;
            try {
                result = Files.move(Paths.get(fileNamesInInputFolder.get(i)), Paths.get("src\\Files\\Archive\\" + formatDateTimeForSaveToArchive + "_" + fileNamesInInputFolder.get(i).substring(16)));
            } catch (IOException e) {
                System.out.println("Exception while moving file: " + e.getMessage());
            }
            if (result != null) {
                reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | перемещён в архив\n");
                reportWriter.writeToReportFile(reportToFile);
            } else {
                reportToFile = (formatDateTime + " | " + fileNamesInInputFolder.get(i).substring(16) + " | не уалось переместить в архив\n");
                reportWriter.writeToReportFile(reportToFile);
            }
        }
        reportToFile = (formatDateTime + " | " + "парсинг файлов закончен\n");
        reportWriter.writeToReportFile(reportToFile);
        return accountsMap;
    }
}