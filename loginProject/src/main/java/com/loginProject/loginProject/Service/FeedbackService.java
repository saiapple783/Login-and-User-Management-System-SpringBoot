package com.loginProject.loginProject.Service;

import com.loginProject.loginProject.Model.Feedback;
import com.loginProject.loginProject.Repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FeedbackService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public void saveForumData(Feedback feedback) {

        feedbackRepo.save(feedback);
    }
}
