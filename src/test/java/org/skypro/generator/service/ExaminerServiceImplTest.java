package org.skypro.generator.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.generator.exception.BadAmountException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.QuestionService;
import org.skypro.generator.service.exam.ExaminerServiceImpl;

import java.util.Collection;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@ExtendWith(MockitoExtension.class)
public class ExaminerServiceImplTest {

    @Mock
    private QuestionService questionService;

    @InjectMocks
    private ExaminerServiceImpl examinerService;

    @Test
    @DisplayName("1. Возврат пустого списка, если amount = 0")
    void shouldReturnEmptyCollectionWhenAmountIsZero() {
        Mockito.when(questionService.getAll()).thenReturn(List.of());

        Collection<Question> result = examinerService.getQuestions(0);
        assertThat(result).isEmpty();
    }

    @Test
    @DisplayName("2. Выброс исключения, если запрошено больше вопросов, чем есть в базе")
    void shouldThrowExceptionWhenAmountIsTooBig() {
        Question q1 = new Question("Q1", "A1");
        Mockito.when(questionService.getAll()).thenReturn(List.of(q1));

        assertThrows(BadAmountException.class, () -> examinerService.getQuestions(5));
    }

    @Test
    @DisplayName("3. Успешный сбор нужного количества уникальных вопросов")
    void shouldReturnCorrectAmountOfUniqueQuestions() {
        Question q1 = new Question("Q1", "A1");
        Question q2 = new Question("Q2", "A2");

        Mockito.when(questionService.getAll()).thenReturn(List.of(q1, q2));

        Mockito.when(questionService.getRandomQuestion()).thenReturn(q1, q1, q2);

        Collection<Question> result = examinerService.getQuestions(2);

        assertThat(result).hasSize(2).contains(q1, q2);

        Mockito.verify(questionService, Mockito.times(3)).getRandomQuestion();
    }
}