package editor.components;

import editor.FileWorker;
import editor.Searcher;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MenuBar extends JMenuBar {
    FileWorker fileWorker;
    JTextArea textArea;
    Searcher searcher;
    Panel panel;

    public MenuBar(Searcher searcher, FileWorker fileWorker, JTextArea textArea, Panel panel) {
        this.fileWorker = fileWorker;
        this.textArea = textArea;
        this.searcher = searcher;
        this.panel = panel;

        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("File");
        fileMenu.setName("MenuFile");
        fileMenu.setMnemonic(KeyEvent.VK_F);

        JMenuItem save = new JMenuItem("Save");
        save.setName("MenuSave");
        save.addActionListener(actionEvent -> {
            fileWorker.save(textArea.getText());
        });

        JMenuItem load = new JMenuItem("Open");
        load.setName("MenuOpen");
        load.addActionListener(actionEvent -> {
            textArea.setText(fileWorker.load());
        });

        JMenuItem exit = new JMenuItem("Exit");
        exit.setName("MenuExit");
        exit.addActionListener(actionEvent -> {
            System.exit(0);
        });


        fileMenu.add(save);
        fileMenu.add(load);
        fileMenu.addSeparator();
        fileMenu.add(exit);


        JMenu searchMenu = new JMenu("Search");
        searchMenu.setName("MenuSearch");
        searchMenu.setMnemonic(KeyEvent.VK_S);

        JMenuItem search = new JMenuItem("Start search");
        search.setName("MenuStartSearch");
        search.addActionListener(actionEvent -> {
            searcher.search(panel.searchField.getText(), panel.regCheck.isSelected());
        });

        JMenuItem next = new JMenuItem("Next match");
        next.setName("MenuNextMatch");
        next.addActionListener(actionEvent -> {
            searcher.next();
        });

        JMenuItem previous = new JMenuItem("Previous match");
        previous.setName("MenuPreviousMatch");
        previous.addActionListener(actionEvent -> {
            searcher.prev();
        });

        JMenuItem regEx = new JMenuItem("Use regular expressions");
        regEx.setName("MenuUseRegExp");
        regEx.addActionListener(actionEvent -> {
            if (panel.regCheck.isSelected()) {
                panel.regCheck.setSelected(false);
            } else {
                panel.regCheck.setSelected(true);
            }
        });


        searchMenu.add(search);
        searchMenu.add(previous);
        searchMenu.add(next);
        searchMenu.add(regEx);

        add(fileMenu);
        add(searchMenu);
    }
}
