package com.bugtracker.bugtracker.controller;

import com.bugtracker.bugtracker.entity.Bug;
import com.bugtracker.bugtracker.service.BugService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.data.domain.Page;

import java.util.List;

@RestController
@RequestMapping("/bugs")
public class BugController {

    @Autowired
    private BugService bugService;

    @PostMapping
    public Bug createBug(@RequestBody Bug bug) {
        return bugService.saveBug(bug);
    }

    @GetMapping
    public List<Bug> getAllBugs() {
        return bugService.getAllBugs();
    }
    @PutMapping("/{id}")
    public Bug updateBug(@PathVariable Long id, @RequestBody Bug bug) {
        Bug existingBug = bugService.getBugById(id);

        existingBug.setTitle(bug.getTitle());
        existingBug.setDescription(bug.getDescription());
        existingBug.setStatus(bug.getStatus());
        existingBug.setPriority(bug.getPriority());

        return bugService.saveBug(existingBug);
    }

    @DeleteMapping("/{id}")
    public String deleteBug(@PathVariable Long id) {
        bugService.deleteBug(id);
        return "Bug deleted successfully";
    }

    @GetMapping("/page")
    public Page<Bug> getBugs(
        @RequestParam int page,
        @RequestParam int size) {

    return bugService.getBugsWithPagination(page, size);
}  
}