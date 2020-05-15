package editor.components;

import editor.FileWorker;
import editor.Searcher;

import javax.swing.*;
import java.awt.*;
import java.util.concurrent.Flow;

public class Panel extends JPanel {
    private FileWorker fileWorker;
    private Searcher searcher;
    private JTextArea textArea;
    public JTextField searchField = new JTextField(10);
    public JCheckBox regCheck = new JCheckBox();

    public Panel(Searcher searcher, FileWorker fileWorker, JTextArea textArea) {

        this.fileWorker = fileWorker;
        this.searcher = searcher;
        this.textArea = textArea;

        setLayout(new FlowLayout());


        regCheck.setName("UseRegExCheckbox");
        searchField.setSize(100, 20);
        searchField.setName("SearchField");

        JButton searchButton = new JButton();
        searchButton.setName("StartSearchButton");
        searchButton.setText("Search");
        searchButton.addActionListener(actionEvent -> {
            searcher.search(searchField.getText(), regCheck.isSelected());
        });

        JButton nextMatchButton = new JButton();
        nextMatchButton.setName("NextMatchButton");
        nextMatchButton.setText("Next");
        nextMatchButton.addActionListener(actionEvent -> {
            searcher.next();
        });

        JButton previousMatchButton = new JButton();
        previousMatchButton.setName("PreviousMatchButton");
        previousMatchButton.setText("Prev");
        previousMatchButton.addActionListener(actionEvent -> {
            searcher.prev();
        });

        //buttons
        JButton saveButton = new JButton();
        saveButton.setName("SaveButton");
        saveButton.setText("Save");
        saveButton.addActionListener(actionEvent -> {
            fileWorker.save(textArea.getText());
        });

        JButton loadButton = new JButton();
        loadButton.setName("OpenButton");
        loadButton.setText("Open");
        loadButton.setSize(100, 20);
        loadButton.addActionListener(actionEvent -> {
            textArea.setText(fileWorker.load());
        });

        add(saveButton);
        add(loadButton);
        add(searchField);
        add(searchButton);
        add(previousMatchButton);
        add(nextMatchButton);
        add(new JLabel("RegEx"));
        add(regCheck);
    }
}
