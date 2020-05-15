package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class FileWorker {
    FileOpener fileOpener;

    public FileWorker(FileOpener fileOpener) {
        this.fileOpener = fileOpener;
    }

    public boolean save(String text) {
        try (FileWriter writer = new FileWriter(openFile(), false)) {
            writer.write(text);
            return true;
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
            return false;
        }
    }

    public String load() {
        StringBuilder sb = new StringBuilder();
        try (InputStream is = new FileInputStream(openFile())) {
            byte[] string = is.readAllBytes();
            return new String(string);
        } catch (IOException e) {
            System.out.printf("An exception occurs %s", e.getMessage());
            return "";
        }
    }

    private File openFile() {
        return new File(fileOpener.getPath());
    }


}

