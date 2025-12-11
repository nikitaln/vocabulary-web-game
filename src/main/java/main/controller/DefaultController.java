package main.controller;

import main.dto.EnWord;
import main.dto.ruWord;
import main.model.Word;
import main.service.CheckWordService;
import main.service.StorageWords;
import main.service.VocabularyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
 * TODO: Добавить алгоритм проверки русских слов
 * TODO: Очищать поле ввода русского слова в JavaScript после каждой проверки
 * TODO: Если ответ true - вывод нового слова - автоматически
 * TODO: Добавить счет правильных слов и не правильных слов (статистика)
 * TODO: Добавить кнопку помощи (перевод слова)
 * TODO: Создавать отдельную коллекцию не переведенных слов
 */

@Controller
@RequestMapping()
public class DefaultController {

    StorageWords storageWords = new StorageWords();
    CheckWordService checkWordService = new CheckWordService();
    @Autowired
    VocabularyService vocabularyService;


    @GetMapping("/")
    public String index(Model model) {
        System.out.println("GET-method: /");
        return "index";
    }


    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ResponseEntity<?> checkWord(ruWord word) {
        System.out.println("POST-method: /check");
        System.out.println("id=" + word.getId());
        System.out.println("ruWord=" + word.getRuWord());

        String ruWordFromDB = vocabularyService.getWordById(word.getId()).getRuWord();
        String ruWordFromWeb = word.getRuWord();

        if (ruWordFromDB.equals(ruWordFromWeb)) {
            /**
             * Сюда можно вписать реализацию счетчика слов
             */
            System.out.println("Слово верно");
            vocabularyService.addNumberCorrectWords();
            return ResponseEntity.ok("true");

        } else return ResponseEntity.ok("false");
    }


    @RequestMapping(value = "/newWord", method = RequestMethod.GET)
    public ResponseEntity<EnWord> getNewRandomWord() {
        Word word = vocabularyService.getRandomWord();
        System.out.println("GET-method: /newWord (" + word.getEnWord() + ")" + " / (" + word.getRuWord() + ")");
        EnWord enWord = new EnWord(word.getId(), word.getEnWord());
        return ResponseEntity.ok(enWord);
    }
}
