package main.repositories;

import main.model.Word;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WordsRepository extends CrudRepository<Word, Integer> {
}
