package com.yash.quizapp.service;

import com.yash.quizapp.dao.QuizDao;
import com.yash.quizapp.model.Question;
import com.yash.quizapp.dao.QuestionDao;
import com.yash.quizapp.model.Quiz;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class QuestionService {

    @Autowired
    QuestionDao questionDao;
    @Autowired
    QuizDao quizDao;

    public ResponseEntity<List<Question>> getAllQuestions() {

        try {
            return new ResponseEntity<>(questionDao.findAll(), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<List<Question>> getQuestionsByCategoryIgnoreCase(String category) {
        try {
            return new ResponseEntity<>(questionDao.findByCategoryIgnoreCase(category), HttpStatus.OK);
        }catch(Exception e){
            e.printStackTrace();
        }
        return new ResponseEntity<>(new ArrayList<>(), HttpStatus.BAD_REQUEST);

    }

    public ResponseEntity<Object> addQuestion(Question question) {
        try {
            questionDao.save(question);
            return new ResponseEntity<>("success", HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("error", HttpStatus.BAD_REQUEST);
        }
    }


    public ResponseEntity<String> deleteQuestion(Integer id) {
        Optional<Question> existingQuestion = questionDao.findById(id);
        if (existingQuestion.isPresent()) {
            Question question = existingQuestion.get();

            // Remove the question from all quizzes before deleting it
            List<Quiz> quizzes = quizDao.findAll();
            for (Quiz quiz : quizzes) {
                quiz.getQuestions().remove(question);
                quizDao.save(quiz);  // Update the quiz to reflect removal
            }

            questionDao.delete(question); // Now safe to delete
            return new ResponseEntity<>("Question deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }

    public ResponseEntity<String> updateQuestion(Integer id, Question updatedQuestion) {
        Optional<Question> existingQuestion = questionDao.findById(id);  // Find the existing question
        if (existingQuestion.isPresent()) {
            Question question = existingQuestion.get();

            // Update fields
            question.setQuestionTitle(updatedQuestion.getQuestionTitle());
            question.setOption1(updatedQuestion.getOption1());
            question.setOption2(updatedQuestion.getOption2());
            question.setOption3(updatedQuestion.getOption3());
            question.setOption4(updatedQuestion.getOption4());
            question.setRightAnswer(updatedQuestion.getRightAnswer());
            question.setDifficultyLevel(updatedQuestion.getDifficultyLevel());
            question.setCategory(updatedQuestion.getCategory());

            questionDao.save(question);  // Save the updated question
            return new ResponseEntity<>("Question updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
    }
}
