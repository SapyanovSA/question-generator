package org.skypro.generator.service;

import org.skypro.generator.model.Question;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class JavaQuestionService implements QuestionService {

    private final Set<Question> questions = new HashSet<>();
    private final Random random = new Random();

    public JavaQuestionService() {
        addTestData();
    }

    private void addTestData() {
        add("Что такое Стрим API в Java?", "Инструмент для обработки наборов данных в функциональном стиле.");
        add("Чем отличается List от Set?", "List хранит элементы по порядку и допускает дубликаты, а Set хранит только уникальные элементы.");
        add("Что делает метод hashCode()?", "Возвращает числовое представление объекта для эффективного поиска в HashMap/HashSet.");
        add("Какое базовое исключение у RuntimeException?", "Оно является необрабатываемым (Unchecked) и не требует обязательного try-catch.");
        add("В чем разница между == и equals()?", "Оператор == сравнивает ссылки в памяти, а метод equals() сравнивает внутреннее содержимое объектов.");
    }

    //Добавление по объекту
    @Override
    public Question add(Question question) {

        if (question == null || question.getQuestion().isBlank() || question.getAnswer().isBlank()) {
            return null;
        }
        questions.add(question);
        return question;
    }

    //Добавление с объектом
    @Override
    public Question add(String question, String answer) {

        Optional<String> qValue = Optional.ofNullable(question)
                .filter(q -> !q.isBlank());

        Optional<String> aValue = Optional.ofNullable(answer)
                .filter(a -> !a.isBlank());

        return qValue.flatMap(q ->
            aValue.map( a -> {
                Question newQuestion = new Question(q, a);
                questions.add(newQuestion);
                return newQuestion;
            })
        ).orElse(null);

    }

    //Удаление
    @Override
    public void remove(Question question) {
        Optional.ofNullable(question).ifPresent(questions::remove);
    }

    //Показ всех вопросов
    @Override
    public Collection<Question> getAll() {

        return questions.stream()
                .collect(Collectors.toUnmodifiableSet());
    }

    //Вызов рандомного вопроса
    @Override
    public Question getRandomQuestion() {
        if (questions.isEmpty()) {
            return null;
        }

        int randomIndex = random.nextInt(questions.size());

        return questions.stream()
                .skip(randomIndex)
                .findFirst()
                .orElse(null);
    }
}
