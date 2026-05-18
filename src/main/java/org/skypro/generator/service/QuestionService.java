package org.skypro.generator.service;

import org.skypro.generator.model.Question;

import java.util.Collection;
import java.util.UUID;

public interface QuestionService {

    //Добавтить объект
    Question add(Question question);

    //Создать объект
    Question add(String question, String answer);

    //Удалить вопрос
    void remove(Question question);

    //Получить список всех вопросов
    Collection<Question> getAll();

    //Выдать рандомные вопросы
    Question getRandomQuestion();

}
