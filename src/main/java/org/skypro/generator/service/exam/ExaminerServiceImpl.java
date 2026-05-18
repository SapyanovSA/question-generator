package org.skypro.generator.service.exam;

import org.skypro.generator.exception.BadAmountException;
import org.skypro.generator.model.Question;
import org.skypro.generator.service.QuestionService;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ExaminerServiceImpl implements ExaminerService {

    private final QuestionService questionService;

    public ExaminerServiceImpl(QuestionService questionService) {
        this.questionService = questionService;
    }

    @Override
    public Collection<Question> getQuestions(int amount) {
        if (amount < 0) {
            throw new BadAmountException("Количество вопросов не может быть отрицательным");
        }

        if (amount > questionService.getAll().size()) {
            throw new BadAmountException("Кол-во запрошенных вопросов больше чем их всего есть!");
        }

        Set<Question> examQuestions = new HashSet<>();

        while (examQuestions.size() < amount) {
            examQuestions.add(questionService.getRandomQuestion());
        }

        return examQuestions;
    }
}
