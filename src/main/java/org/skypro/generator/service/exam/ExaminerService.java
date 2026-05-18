package org.skypro.generator.service.exam;

import org.skypro.generator.model.Question;

import java.util.Collection;

public interface ExaminerService {

    Collection<Question> getQuestions(int amount);

}
