package com.jawad.jobs.services;

import com.jawad.jobs.models.Job;
import com.jawad.jobs.models.JobType;
import com.jawad.jobs.repositories.IJobRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JobService {

    @Autowired
    IJobRepository iJobRepository;

    // Using CrudRepository -->
    public String addJobsToDb(List<Job> jobs) {
        iJobRepository.saveAll(jobs);
        return "Jobs are added!";
    }
    public List<Job> getJobsFromDb() {
        return (List<Job>) iJobRepository.findAll();
    }
    public String updateJobById(Job updatedJob) {
        Job oldJob = iJobRepository.findById(updatedJob.getId()).orElse(null);
        if(oldJob != null){
            iJobRepository.save(updatedJob);
            return "Job updated!";
        }
        else {
            return "Id is not valid!";
        }
    }
    public String deleteJobByIdFromDb(Long id) {
        iJobRepository.deleteById(id);
        return "Job deleted!";
    }

    // Custom Finder methods -->
    public List<Job> getJobBySalaryFromDb(Double salary) {
        return iJobRepository.findBySalary(salary);
    }
    public List<Job> getJobsBySalaryGreaterDescAppliedDate(Double salary) {
        return iJobRepository.findBySalaryGreaterThanOrderByAppliedDateDesc(salary);
    }
    public List<Job> getJobsDescriptionHaving(String str) {
        return iJobRepository.findByDescriptionContains(str);
    }
    public List<Job> getJobsByJobTypeNot(JobType jobType) {
        return iJobRepository.findByJobTypeNot(jobType);
    }

    // Using Native Query Methods -->
    @Transactional
    public String updateLocationById(String location, Long id) {
        iJobRepository.updateLocationById(id, location);
        return "Location updated!";
    }
    @Transactional
    public String deleteByIdNative(Long id) {
        iJobRepository.deleteJobById(id);
        return "Job deleted!";
    }
    public List<Job> getAllJobsByTitle(String title) {
        return iJobRepository.selectJobByTitle(title);
    }
    public List<Job> getAllJobsByDescription(String description) {
        return iJobRepository.selectJobByDescription(description);
    }
}
