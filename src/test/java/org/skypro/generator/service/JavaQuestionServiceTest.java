package org.skypro.generator.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.skypro.generator.model.Question;

import java.util.Collection;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class JavaQuestionServiceTest {
    private JavaQuestionService questionService;

    @BeforeEach
    void setUp() {
        questionService = new JavaQuestionService();
    }

    @Test
    @DisplayName("1. Добавление вопроса и проверка работы с дубликатами")
    void addQuestionAndIgnoreDuplication() {

        Question q1 = questionService.add("В каком году крестили Русь?", "988");
        Question q2 = questionService.add("В каком году крестили Русь?", "988");

        Collection<Question> all = questionService.getAll();
        assertThat(all).hasSize(1).contains(q1);
    }

    @Test
    @DisplayName("2. Добавление вопроса по объекту с проверкой валидации полей")
    void addQuestionByObjectAndValidateFields() {
        Question validQuestion = new Question("В каком году крестили Русь?", "988");
        Question invalidQuestion = new Question("", " ");

        assertThat(questionService.add(validQuestion)).isEqualTo(validQuestion);
        assertThat(questionService.add(invalidQuestion)).isNull();
        assertThat(questionService.add((Question) null)).isNull();
    }

    @Test
    @DisplayName("3. Удаление вопроса и работа с пустой коллекцией")
    void removeQuestionAndHandleEmptyStorage() {
        Question testQuestion = new Question("Test Q", "Test A");

        questionService.remove(testQuestion);
        assertThat(questionService.getAll()).isEmpty();

        questionService.add(testQuestion);
        questionService.remove(testQuestion);
        assertThat(questionService.getAll()).isEmpty();
    }

    @Test
    @DisplayName("4. Получение случайного вопроса")
    void returnRandomQuestionWhenNotEmpty() {

        assertThat(questionService.getRandomQuestion()).isNull();

        Question q1 = questionService.add("Q1", "A1");
        Question q2 = questionService.add("Q2", "A2");

        Question random = questionService.getRandomQuestion();

        assertThat(random).isIn(q1, q2);
    }

}
