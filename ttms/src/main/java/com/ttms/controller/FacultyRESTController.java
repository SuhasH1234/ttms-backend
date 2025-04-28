package com.ttms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ttms.model.FacultyUser;
import com.ttms.service.FacultyService;

@RestController
@RequestMapping("/faculty")
@CrossOrigin(origins = "http://localhost:3000")
public class FacultyRESTController {
    
    @Autowired
    private FacultyService facultyService;

    @PostMapping("/login")
    public ResponseEntity<String> loginFacultyUser(@RequestBody FacultyUser facultyUser) {
        // Check if the faculty credentials are valid
    	FacultyUser existingFaculty = facultyService.findByEmailAndPassword(facultyUser.getFacultyEmail(), facultyUser.getFacultyPassword());

        if (existingFaculty != null) {
            // If valid, return success and status code
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // If invalid credentials, return error message
            return new ResponseEntity<>("Invalid credentials", HttpStatus.UNAUTHORIZED);
        }
    }

    // Faculty Registration API (for new users)
    @PostMapping("/register")
    public ResponseEntity<String> createFacultyUser(@RequestBody FacultyUser facultyUser) {
        String status = facultyService.upsert(facultyUser);
        return new ResponseEntity<>(status, HttpStatus.CREATED);
    }

    @GetMapping("/{facultyid}")
    public ResponseEntity<FacultyUser> getFacultyById(@PathVariable Integer facultyid) {
    	FacultyUser facultyUser = facultyService.getFacultyById(facultyid);
        return new ResponseEntity<>(facultyUser, HttpStatus.OK);
    }

    @GetMapping("/all-faculty")
    public ResponseEntity<List<FacultyUser>> getAllFacultyUsers() {
        List<FacultyUser> allFacultyUsers = facultyService.getAllFacultyUsers();
        return new ResponseEntity<>(allFacultyUsers, HttpStatus.OK);
    }
    
    @PutMapping("/update-faculty")
	public ResponseEntity<String> updateFaculty(@RequestBody FacultyUser facultyUser) {
		String status= facultyService.upsert(facultyUser);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
	
	@DeleteMapping("/delete/{facultyid}")
	public ResponseEntity<String> deleteCourse(@PathVariable Integer facultyid) {
		String status= facultyService.deleteById(facultyid);
		return new ResponseEntity<>(status,HttpStatus.OK);
	}
}