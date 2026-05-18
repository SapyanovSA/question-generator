package org.skypro.generator.controller;

import org.skypro.generator.model.Question;
import org.skypro.generator.service.exam.ExaminerService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
public class ExamController {

    private final ExaminerService examinerService;

    public ExamController(ExaminerService examinerService) {
        this.examinerService = examinerService;
    }

    @GetMapping("/exam/get/{amount}")
    public Collection<Question> getQuestions(@PathVariable int amount) {
        return examinerService.getQuestions(amount);
    }

}
