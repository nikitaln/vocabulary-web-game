package main.service;

import main.model.Word;
import main.repositories.WordsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.SocketHandler;

@Service
public class VocabularyService {

    WordsRepository wordsRepository;

    @Autowired
    public VocabularyService(WordsRepository wordsRepository) {
        this.wordsRepository = wordsRepository;
    }

    //сохранение всех слов
    private static List<Word> words = new ArrayList<>();
    private static String path = "C:\\Users\\admin\\Desktop\\english_vocabulary_data\\words.txt";


    public void parseFileTxt(String pathToFile) {

        String regexEng = "[A-z]+"; //поиск только латинских символов
        String regexRu = "[А-я,]+"; //поиск русских символов и запятых
        int iterator = 1;

        try {
            List<String> allLines = Files.readAllLines(Path.of(pathToFile));

            for (String line : allLines) {
                String englishWord = "";
                String russianWord = "";

                String[] elements = line.split(";");
                for (int i = 1; i < elements.length; i++) {

                    if (elements[i].matches(regexEng)) {
//                        System.out.println(iterator + " true");
//                        System.out.println(elements[i]);
                        englishWord = englishWord + elements[i] + " ";
                    } else if (elements[i].matches(regexRu)) {
                        russianWord = russianWord + elements[i] + " ";
                    }
                }
                iterator = iterator + 1;
                //каждая английская фраза выделена в отдельную строку
                //которую можно добавлять в Мап в качестве ключа
                //можно создать метод getEnglishPhrase
                //и метод getRussianPhrase
                //нужны отдельные строки на английском и на русском
                englishWord = englishWord.trim(); //удаляем лишние пробелы в конце слов
                russianWord = russianWord.trim();
                System.out.println(iterator + " "+ englishWord + " = " + russianWord);
                //создаем объект Word и сохраняем в List words
                storeToList(createWordObject(englishWord, russianWord));
            }

        } catch (IOException e) {
            throw new RuntimeException(e);
        }


    }


    private static Word createWordObject(String englishWord, String russianWord) {
        Word word = new Word();
        word.setEnWord(englishWord);
        word.setRuWord(russianWord);
        return word;
    }


    private static void storeToList(Word word) {
        words.add(word);
    }


    private static void getSizeWordsList() {
        System.out.println("size = " + words.size());
    }


    public void saveToDataBase() {
        wordsRepository.saveAll(words);
        System.out.println("Добавили в БД");
    }


    public Word getRandomWord() {
        int minId = 1;
        int maxId = 5000;
        int random = (int) (Math.random() * (maxId - minId) + minId);
        return wordsRepository.findById(random).get();
    }


    public Word getWordById(int id) {
        return wordsRepository.findById(id).get();
    }
}
