package com.jawad.jobs.repositories;

import com.jawad.jobs.models.Job;
import com.jawad.jobs.models.JobType;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IJobRepository extends CrudRepository<Job, Long> {

    // Custom Finder Methods -->>
    public List<Job> findBySalary(Double salary);
    public List<Job> findBySalaryGreaterThanOrderByAppliedDateDesc(Double salary);
    public List<Job> findByDescriptionContains(String str);
    public List<Job> findByJobTypeNot(JobType jobType);

    // Native Custom Query -->>
    @Modifying
    @Query(value = "update jobs set location = :location where id = :id", nativeQuery = true)
    public void updateLocationById(Long id, String location);
    @Modifying
    @Query(value = "delete from jobs where id = :id", nativeQuery = true)
    public void deleteJobById(Long id);
    @Query(value = "select * from jobs where title = :title", nativeQuery = true)
    public List<Job> selectJobByTitle(String title);
    @Query(value = "select * from jobs where description = :description", nativeQuery = true)
    public List<Job> selectJobByDescription(String description);
}
