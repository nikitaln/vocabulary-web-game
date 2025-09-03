package main.model;

import jakarta.persistence.*;

@Entity
@Table(name = "vocabulary")
public class Word {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ruWord", columnDefinition = "VARCHAR(255) NOT NULL")
    private String ruWord;
    @Column(name = "enWord", columnDefinition = "VARCHAR(255) NOT NULL")
    private String enWord;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRuWord() {
        return ruWord;
    }

    public void setRuWord(String ruWord) {
        this.ruWord = ruWord;
    }

    public String getEnWord() {
        return enWord;
    }

    public void setEnWord(String enWord) {
        this.enWord = enWord;
    }
}
