package com.example.demo.controller;


import com.example.demo.entity.Session;
import com.example.demo.repository.SessionRepository;

import java.time.LocalDateTime;
import java.util.List;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class GameController {
    
    @Autowired
    private SessionRepository sessionRepository;

    @PostMapping("/sessions")
    public ResponseEntity<Session> createSession(@RequestBody Session session) {
        // Generate unique session ID
        String sessionId = UUID.randomUUID().toString();
        session.setSessionId(sessionId);

        // Save the session to the database
        session.setStartTime(LocalDateTime.now());
        Session savedSession = sessionRepository.save(session);

        return ResponseEntity.ok(savedSession);
    }

    @GetMapping("/sessions/{sessionId}")
    public ResponseEntity<Session> getSession(@PathVariable String sessionId) {
        // Retrieve session from database
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        // Return session if found
        if (optionalSession.isPresent()) {
            return ResponseEntity.ok(optionalSession.get());
        }

        // Return 404 error if session not found
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/sessions/{sessionId}/numbers")
    public ResponseEntity<Session> addNumberToSession(@PathVariable String sessionId, @RequestBody Integer number) {
        // Retrieve session from database
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        // Return 404 error if session not found
        if (!optionalSession.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Session session = optionalSession.get();

        // Add number to the session's list of numbers
        session.getNumbers().add(number);

        // Save the updated session to the database
        Session savedSession = sessionRepository.save(session);

        // Return the updated session
        return ResponseEntity.ok(savedSession);
    }

    @GetMapping("/sessions/{sessionId}/numbers")
    public ResponseEntity<List<Integer>> getSessionNumbers(@PathVariable String sessionId) {
        // Retrieve session from database
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        // Return 404 error if session not found
        if (!optionalSession.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Return the session's list of numbers
        Session session = optionalSession.get();
        return ResponseEntity.ok(session.getNumbers());
    }

    @PostMapping("/sessions/{sessionId}/result")
    public ResponseEntity<String> saveResult(@PathVariable String sessionId, @RequestBody Result result) {
        // Retrieve session from database
        Optional<Session> optionalSession = sessionRepository.findById(sessionId);

        // Return 404 error if session not found
        if (!optionalSession.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        // Update the session with the result data and save to database
        Session session = optionalSession.get();
        session.setEndTime(result.getTime());
        session.setSumOfSelectedNumbers(result.getSumOfNumbers());
        session.setResult(result.getResult());
        sessionRepository.save(session);

        return ResponseEntity.ok("Result saved successfully!");
    }
}
