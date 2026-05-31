package com.mseada.babysitter.controller;

import com.mseada.babysitter.model.Babysitter;
import com.mseada.babysitter.service.BabysitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/babysitters")
@CrossOrigin(origins = "*")
public class BabysitterController {

    @Autowired
    private BabysitterService babysitterService;

    @GetMapping
    public List<Babysitter> list(
            @RequestParam(required = false) String search,
            @RequestParam(required = false) Double minRating,
            @RequestParam(required = false) String availability,
            @RequestParam(required = false) String area) {
        return babysitterService.search(search, minRating, availability, area);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Babysitter> getById(@PathVariable String id) {
        Babysitter bs = babysitterService.findById(id);
        if (bs == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(bs);
    }

    @GetMapping("/areas")
    public List<String> areas() {
        return babysitterService.findAllAreas();
    }
}
