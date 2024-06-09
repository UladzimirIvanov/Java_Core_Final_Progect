import java.io.File;

public class FileFoundInInput {
    public File[] readInputFilesName() {
        File folder = new File("src\\Files\\Input");
        File[] files = folder.listFiles();
                /*Path result = null;
                try {
                    result =  Files.move(Paths.get("src\\Files\\Input\\" + files[i].getName()), Paths.get("src\\Files\\Archive\\" + files[i].getName()));
                } catch (IOException e) {
                    System.out.println("Exception while moving file: " + e.getMessage());
                }
                if(result != null) {
                    System.out.println("File moved successfully.");
                }else{
                    System.out.println("File movement failed.");
                }*/
        return files;
    }
}
