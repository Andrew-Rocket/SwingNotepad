package editor;

import javax.swing.*;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Searcher {
    JTextArea textArea;

    //map
    List<Integer> indexes = new LinkedList<>();
    List<Integer> sizes = new LinkedList<>();
    private int size;
    private int current = 0;

    public Searcher(JTextArea textArea) {
        this.textArea = textArea;
    }

    private synchronized void searchByString(String pattern) {
        Thread searchThread = new Thread(() -> {
            String text = textArea.getText();

            size = pattern.length();
            int index = -1;

            do {
                index = text.indexOf(pattern, index + 1);
                if (index == -1) {
                    break;
                }
                indexes.add(index);
                sizes.add(size);
            } while (true);

            mark(indexes.get(0), sizes.get(0));
            current = 0;
        });
        searchThread.setName("SearchThread");
        searchThread.start();
    }

    private synchronized void searchByRegex(String pattern) {

        Thread searchThread = new Thread(() -> {
            String text = textArea.getText();

            Matcher matcher = Pattern.compile(pattern)
                    .matcher(text);
            while (matcher.find()) {
                indexes.add(matcher.start());
                sizes.add(matcher.group().length());
            }
            mark(indexes.get(0), sizes.get(0));
            current = 0;
        });


        searchThread.setName("SearchThread");
        searchThread.start();
    }

    public void search(String pattern, boolean reg) {
        clear();
        if (reg) {
            searchByRegex(pattern);
        } else {
            searchByString(pattern);
        }
    }


    public void next() {
        if (current < indexes.size() - 1) {
            current++;
        } else {
            current = 0;
        }
        mark(indexes.get(current), sizes.get(current));
    }

    public void prev() {
        if (current > 0) {
            current--;
        } else {
            current = indexes.size() - 1;
        }
        mark(indexes.get(current), sizes.get(current));
    }

    private void mark(int index, int size) {
        textArea.setCaretPosition(index + size);
        textArea.select(index, index + size);
        textArea.grabFocus();

    }

    public void clear() {
        indexes.clear();
        sizes.clear();
    }
}

