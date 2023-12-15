package com.project.webxaydung.Controllers;
import com.project.webxaydung.Dto.JobOpeningDTO;
import com.project.webxaydung.Models.JobOpening;
import com.project.webxaydung.Services.JobOpeningService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("${api.prefix}/jobopening")
@RequiredArgsConstructor
@Validated
public class JobOpeningController {
    private final JobOpeningService jobOpeningService;
    @PostMapping("")
    public ResponseEntity<?> insertJob(@RequestBody JobOpeningDTO jobOpeningDTO, BindingResult result){
        if(result.hasErrors()) {
            List<String> errorMessages = result.getFieldErrors()
                    .stream()
                    .map(FieldError::getDefaultMessage)
                    .toList();
            return ResponseEntity.badRequest().body(errorMessages);
        }
            jobOpeningService.insertJobopening(jobOpeningDTO);
            return ResponseEntity.ok("Insert job Successfully");

    }

    @GetMapping("")// http://localhost:8080/api/v1/contact
    public ResponseEntity<List<JobOpening>> getAllJobs(){
        List<JobOpening> jobOpenings=jobOpeningService.getAllJobs();
        return  ResponseEntity.ok(jobOpenings);
    }
    @GetMapping("/{id}")
    public ResponseEntity<?> getJobById(
            @PathVariable("id") int id
    ) {
        jobOpeningService.getJobById(id);
        return ResponseEntity.ok("Job with ID: " +id+ jobOpeningService.getJobById(id));
    }
    @GetMapping("/available")
    public List<JobOpening> getAvailableJobOpenings() {
        return jobOpeningService.getJobAvailable();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletedJob(@PathVariable("id") int id){
        jobOpeningService.deletedjob(id);
        return ResponseEntity.ok("xóa thành công");
    }

}