package com.bugtracker.bugtracker.service;

import com.bugtracker.bugtracker.entity.Bug;
import com.bugtracker.bugtracker.entity.Priority;
import com.bugtracker.bugtracker.entity.Status;
import com.bugtracker.bugtracker.entity.User;
import com.bugtracker.bugtracker.repository.BugRepository;
import com.bugtracker.bugtracker.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.List;

@Service
public class BugService {

    @Autowired
    private BugRepository bugRepository;

    @Autowired
    private UserRepository userRepository;

    // CREATE
    public Bug saveBug(Bug bug) {
        return bugRepository.save(bug);
    }

    // GET ALL
    public List<Bug> getAllBugs() {
        return bugRepository.findAll();
    }

    // GET BY ID
    public Bug getBugById(Long id) {
        return bugRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Bug not found with id " + id));
    }

    // DELETE
    public void deleteBug(Long id) {
        bugRepository.deleteById(id);
    }

    // FILTER
    public List<Bug> filterBugs(Status status, Priority priority) {

        if (status != null && priority != null) {
            return bugRepository.findByStatusAndPriority(status, priority);
        } else if (status != null) {
            return bugRepository.findByStatus(status);
        } else if (priority != null) {
            return bugRepository.findByPriority(priority);
        } else {
            return bugRepository.findAll();
        }
    }

    //  ASSIGN BUG TO USER
    public Bug assignBug(Long bugId, Long userId) {
        Bug bug = bugRepository.findById(bugId)
                .orElseThrow(() -> new RuntimeException("Bug not found"));

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        bug.setUser(user);
        return bugRepository.save(bug);
    }

    public Page<Bug> getBugsWithPagination(int page, int size) {
    return bugRepository.findAll(PageRequest.of(page, size));
}
}