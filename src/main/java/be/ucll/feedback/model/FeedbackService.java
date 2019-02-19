package be.ucll.feedback.model;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class FeedbackService {
    List<Feedback> feedbacks = new ArrayList<Feedback>();
    // thread safe int to set id in feedback
    private AtomicInteger nextId = new AtomicInteger();

    // hardcode some values, definitively not the way to go !!!!
    public FeedbackService() {
        feedbacks.add(new Feedback(nextId.incrementAndGet(), "Jozef", "Dat kan hier niet ver meer zijn!"));
        feedbacks.add(new Feedback(nextId.incrementAndGet(), "Maria", "Maar vraag dan toch gewoon eens de weg!"));
    }

    // just return the whole list, Spring takes care of conversion to JSON
    public List<Feedback> getAllFeedbacks() {
        return feedbacks;
    }

    // look for a feedback by id (see controller)
    public Feedback findFeedbackById(int id) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId() == id) {
                return feedback;
            }
        }
        //return null;
        // beter nog: throw exception!
        throw new IllegalArgumentException("You really messed up your numbers!");
    }

    // look for a feedback by name (see controller)
    public List<Feedback> findFeedbackByName(String name) {
        List<Feedback> foundFeedbacks = new ArrayList<Feedback>();
        for (Feedback feedback : feedbacks) {
            if (name.equals(feedback.getName())) {
                foundFeedbacks.add(feedback);
            }
        }
        if(foundFeedbacks.isEmpty()) {
            throw new IllegalArgumentException("No feedback from that person!");
        }
        else {
            return foundFeedbacks;
        }
    }

    public int addFeedback(Feedback feedback) {
        feedback.setId(nextId.incrementAndGet());
        feedbacks.add(feedback);
        return feedback.getId();
    }

    public Feedback changeFeedback(int id, Feedback changedFeedback) {
        for (Feedback feedback : feedbacks) {
            if (feedback.getId() == id) {
                //feedback.setId(id);
                feedback.setName(changedFeedback.getName());
                feedback.setFeedback(changedFeedback.getFeedback());
                return feedback;
            }
        }

        throw new IllegalArgumentException("You really messed up your numbers!");
    }

    public void deleteFeedback(int id) {
        Feedback feedback = this.findFeedbackById(id);
        feedbacks.remove(feedback);
    }
}
