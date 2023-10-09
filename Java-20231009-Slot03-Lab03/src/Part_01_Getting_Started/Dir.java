package Part_01_Getting_Started;

import java.io.File;
public class Dir {
    static void listPath(File path) {
        // get all files (or directories) in File path
        File[] files = path.listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(files[i].toString());
        }
    }
    public static void main(String args[]) {
        listPath(new File("C:\\WINDOWS"));
    }
}
