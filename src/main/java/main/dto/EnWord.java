package main.dto;

public class EnWord {
    private int id;
    private String enWord;


    public EnWord(int id, String enWord) {
        this.id = id;
        this.enWord = enWord;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }
}
