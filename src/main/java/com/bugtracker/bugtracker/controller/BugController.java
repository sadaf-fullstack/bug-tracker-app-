package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.entity.Bug;
import com.bugtracker.bugtracker.entity.User;
import com.bugtracker.bugtracker.service.BugService;
import com.bugtracker.bugtracker.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/bugs")
public class BugController {

    @Autowired
    private BugService bugService;

    @Autowired
    private UserRepository userRepository;

    // ✅ CREATE BUG
    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.saveBug(bug);
    }

    // ✅ GET ALL BUGS
    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }

    // ✅ UPDATE BUG (FINAL FIXED VERSION)
    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug bug) {

        Bug existingBug = bugService.getBugById(id);

        if (existingBug == null) {
            throw new RuntimeException("Bug not found with id " + id);
        }

        // update basic fields
        existingBug.setTitle(bug.getTitle());
        existingBug.setDescription(bug.getDescription());
        existingBug.setStatus(bug.getStatus());
        existingBug.setPriority(bug.getPriority());

        // ✅ SAFE USER ASSIGNMENT (NO 500 ERROR)
        if (bug.getUser() != null && bug.getUser().getId() != null) {
            User user = userRepository.findById(bug.getUser().getId()).orElse(null);

            if (user != null) {
                existingBug.setUser(user);
            }
        }

        return bugService.saveBug(existingBug);
    }

    // ✅ DELETE BUG
    @DeleteMapping("/{id}")
    public String deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return "Bug deleted successfully";
    }

    // ✅ PAGINATION
    @GetMapping("/page")
    public Page<Bug> getBugs(
            @RequestParam int page,
            @RequestParam int size) {

        return bugService.getBugsWithPagination(page, size);
    }
}