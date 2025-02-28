package com.loginProject.loginProject.Service;

import com.loginProject.loginProject.Model.Feedback;
import com.loginProject.loginProject.Repository.FeedbackRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FetchSubmittedService {

    @Autowired
    private FeedbackRepo feedbackRepo;

    public List<Feedback> chckuser(String fetchedUsername){
        List<Feedback> feedbackList = feedbackRepo.findByUsername(fetchedUsername);
        return feedbackList;
    }
}
