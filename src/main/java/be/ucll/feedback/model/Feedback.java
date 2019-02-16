package be.ucll.feedback.model;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class Feedback {
    private int id;

    @NotNull
    @NotEmpty
    @Size(min=2, max=30)
    private String name;

    @NotNull
    @NotEmpty
    @Size(min=5, max=80)
    private String feedback;

    public Feedback() {}

    public Feedback(int id, String name, String feedback) {
        this.id = id;
        this.name = name;
        this.feedback = feedback;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }
}
