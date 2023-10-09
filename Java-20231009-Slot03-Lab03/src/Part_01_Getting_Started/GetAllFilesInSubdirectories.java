package Part_01_Getting_Started;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class GetAllFilesInSubdirectories {
    public static void main(String[] args) {
        String rootDirectoryPath = "C:\\FPT APTECH\\Sem2\\JavaPrograming2-Sem2-NguyenVanHuy";
        List<File> allFiles = getAllFilesInSubdirectories(new File(rootDirectoryPath));

        for (File file : allFiles) {
            System.out.println(file.getAbsolutePath());
        }
    }

    public static List<File> getAllFilesInSubdirectories(File directory) {
        List<File> fileList = new ArrayList<>();

        if (directory.isDirectory()) {
            File[] subFiles = directory.listFiles();
            if (subFiles != null) {
                for (File file : subFiles) {
                    if (file.isDirectory()) {
                        // Recursive call for subdirectory
                        fileList.addAll(getAllFilesInSubdirectories(file));
                    } else {
                        fileList.add(file);
                    }
                }
            }
        }

        return fileList;
    }
}

