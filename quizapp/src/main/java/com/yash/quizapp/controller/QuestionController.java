package com.yash.quizapp.controller;


import com.yash.quizapp.model.Question;
import com.yash.quizapp.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("Question")
public class QuestionController {

        @Autowired
        QuestionService questionService;

        @GetMapping("allQuestions")
        public ResponseEntity<List<Question>> getAllQuestions()
        {
            return questionService.getAllQuestions();

        }

        @GetMapping("category/{category}")
        public ResponseEntity<List<Question>> getQuestionByCategory(@PathVariable String category){
                return questionService.getQuestionsByCategory(category);
        }

        //deletemapping with delete nad updatemapping with put
        @PostMapping("add")
        public ResponseEntity<Object> addQuestion(@RequestBody Question question){
                return questionService.addQuestion(question);
        }
}