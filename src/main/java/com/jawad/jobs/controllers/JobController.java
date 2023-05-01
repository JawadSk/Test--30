package com.jawad.jobs.controllers;

import com.jawad.jobs.models.Job;
import com.jawad.jobs.models.JobType;
import com.jawad.jobs.services.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/")
public class JobController {

    @Autowired
    JobService jobService;

    // Using CrudRepository Methods -->
   @PostMapping(value = "jobs")
    public String addJobs(@RequestBody List<Job> jobs){
        return jobService.addJobsToDb(jobs);
    }
    @GetMapping(value = "jobs")
    public List<Job> getJobs(){
        return jobService.getJobsFromDb();
    }
    @PutMapping(value = "job")
    public String updateJob(@RequestBody Job updatedJob){
        return jobService.updateJobById(updatedJob);
    }
    @DeleteMapping(value = "job/{id}")
    public String deleteJob(@PathVariable Long id){
        return jobService.deleteJobByIdFromDb(id);
    }

    // Using Custom Finder Methods -->
    @GetMapping(value = "jobs/{salary}")
    public List<Job> getJobBySalary(@PathVariable Double salary){
        return jobService.getJobBySalaryFromDb(salary);
    }
    @GetMapping(value = "jobs/salary/greater/{salary}/sort/desc/appliedDate")
    public List<Job> getJobsBySalaryGreaterDescAppliedDate(@PathVariable Double salary){
        return jobService.getJobsBySalaryGreaterDescAppliedDate(salary);
    }
    @GetMapping(value = "/jobs/description/contain/{str}")
    public List<Job> getJobsDescriptionHaving(@PathVariable String str){
        return jobService.getJobsDescriptionHaving(str);
    }
    @GetMapping(value = "jobs/jobType/not/{myType}")
    public List<Job> getJobsByJobTypeNot(@PathVariable String myType){
        return jobService.getJobsByJobTypeNot(JobType.valueOf(myType));
    }

    // Using Native Query Methods -->
    @PutMapping(value = "jobs/location/{location}/id/{id}")
    public String updateLocationById(@PathVariable String location,@PathVariable Long id){
        return jobService.updateLocationById(location, id);
    }
    @DeleteMapping(value = "job/native/{id}")
    public String deleteByIdNative(@PathVariable Long id){
        return jobService.deleteByIdNative(id);
    }
    @GetMapping(value = "jobs/title/{title}")
    public List<Job> getAllJobsByTitle(@PathVariable String title){
        return jobService.getAllJobsByTitle(title);
    }
    @GetMapping(value = "jobs/description/{description}")
    public List<Job> getAllJobsByDescription(@PathVariable String description){
        return jobService.getAllJobsByDescription(description);
    }
}
