package main.service;

import main.model.Word;

import java.util.ArrayList;
import java.util.List;

public class StorageWords {

    private List<Word> words = new ArrayList<>();


    public void addWord(Word word) {
        words.add(word);
    }


    public Word getWordById(int wordId) {
        for (Word word : words) {
            if (word.getId() == wordId) {
                return word;
            }
        }
        return null;
    }
}
