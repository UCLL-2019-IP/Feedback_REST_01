package be.ucll.feedback.controller;

import be.ucll.feedback.model.Feedback;
import be.ucll.feedback.model.FeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class FeedbackController {
    @Autowired
    FeedbackService feedbackService;

    @GetMapping("/hello")
    public String getHelloMessage() {
        return "Hello from Springboot! By Rudy";
    }

    @GetMapping("feedback")
    public List<Feedback> getAllFeedback() {
        return feedbackService.getAllFeedbacks();
    }

    @PostMapping("feedback")
    @ResponseStatus(HttpStatus.CREATED)
    public Feedback createNewFeedback(@RequestBody @Valid Feedback feedback) {
        int id = feedbackService.addFeedback(feedback);
        return feedbackService.findFeedbackById(id);
    }

    @GetMapping("feedback/{foo}")
    public Feedback getSpecificFeedback(@PathVariable("foo") int id) {
        return feedbackService.findFeedbackById(id);
    }

    @PutMapping("feedback/{id}")
    @ResponseStatus(HttpStatus.I_AM_A_TEAPOT)
    public Feedback editSpecificFeedback(@PathVariable("id") int id, @RequestBody @Valid Feedback changedFeedback) {
        return feedbackService.changeFeedback(id, changedFeedback);
    }

    @ResponseStatus(value = HttpStatus.BAD_REQUEST, reason = "Requested ID not found!")
    @ExceptionHandler(value = IllegalArgumentException.class)
    public void badIdExceptionHandler() {
    }
}


