package org.skypro.generator.controller;

import org.skypro.generator.model.Question;
import org.skypro.generator.service.QuestionService;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;

@RestController
@RequestMapping("/exam/java")
public class JavaQuestionController {

    private final QuestionService questionService;

    public JavaQuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @GetMapping
    public Collection<Question> getQuestions(){
        return questionService.getAll();
    }

    @PostMapping("/add")
    public Question add(@RequestParam String question, @RequestParam String answer) {
        return questionService.add(question, answer);
    }

    @DeleteMapping("/remove")
    public Question remove(@RequestParam String question, @RequestParam String answer) {
        Question newQuestion = new Question(question, answer);
        questionService.remove(newQuestion);
        return newQuestion;
    }

}
