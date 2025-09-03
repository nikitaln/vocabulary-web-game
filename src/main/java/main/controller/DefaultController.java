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

//        System.out.println("входящее слово = " + word.getEnWord());
//
//        Word wordById = vocabularyService.getWordById(word.getId());
//
//        if (word.getEnWord().equals(wordById.getEnWord())) {
//            System.out.println("true = " + wordById.getEnWord());
//            return "index";
//        } else {
//            System.out.println("false = " + wordById.getEnWord());
//            return "index";
//        }
        return ResponseEntity.ok("true");
    }


    @RequestMapping(value = "/newWord", method = RequestMethod.GET)
    public ResponseEntity<EnWord> getNewRandomWord() {
        Word word = vocabularyService.getRandomWord();
        System.out.println("GET-method: /newWord (" + word.getEnWord() + ")");
        EnWord enWord = new EnWord(word.getId(), word.getEnWord());
        return ResponseEntity.ok(enWord);
    }
}
