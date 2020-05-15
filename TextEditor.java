package editor;

import editor.components.MenuBar;
import editor.components.Panel;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.filechooser.FileSystemView;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.io.File;

public class TextEditor extends JFrame {

    private Searcher searcher;
    private FileWorker fileWorker;

    public TextEditor() {

        JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
        jfc.setName("FileChooser");
        fileWorker = new FileWorker(new FileOpener(jfc));
        add(jfc);

        //frame
        setSize(550, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        getContentPane().setLayout(new BorderLayout());

        setTitle("JPad");

        //text area
        JTextArea textArea = new JTextArea(20, 20);
        textArea.setName("TextArea");
        textArea.setVisible(true);

        JScrollPane scrollableTextArea = new JScrollPane(textArea);
        scrollableTextArea.setName("ScrollPane");
        scrollableTextArea.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        scrollableTextArea.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        searcher = new Searcher(textArea);
        Panel panel = new Panel(searcher, fileWorker, textArea);
        JMenuBar menuBar = new MenuBar(searcher, fileWorker, textArea, panel);

        //menu
        setJMenuBar(menuBar);

        getContentPane().add(scrollableTextArea, BorderLayout.CENTER);
        getContentPane().add(panel, BorderLayout.PAGE_START);


        setVisible(true);


    }
}
