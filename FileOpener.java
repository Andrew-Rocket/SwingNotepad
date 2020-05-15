package editor;

import javax.swing.*;
import javax.swing.filechooser.FileSystemView;
import java.io.File;

public class FileOpener {

    JFileChooser jfc;
    ;
    private int returnValue;

    public FileOpener(JFileChooser jfc) {
        this.jfc = jfc;
    }

    public String getPath() {
        returnValue = jfc.showOpenDialog(null);
        if (returnValue == JFileChooser.APPROVE_OPTION) {
            File selectedFile = jfc.getSelectedFile();
            return selectedFile.getAbsolutePath();
        }
        return "";
    }
}
