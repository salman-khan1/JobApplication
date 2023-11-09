package com.example.firstjobapp.Job.service.imp;

import com.example.firstjobapp.Job.entity.Job;
import com.example.firstjobapp.Job.repository.JobRepository;
import com.example.firstjobapp.Job.service.JobService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class JobServiceImpl implements JobService {

//    private List<Job> jobs=new ArrayList<> ();
    @Autowired
    JobRepository jobRepository;
    @Override
    public List<Job> findAll () {
        return jobRepository.findAll ();
    }

    @Override
    public void createJob (Job job) {
         jobRepository.save (job);
    }

    @Override
    public Job getJobById (Long id) {
    return jobRepository.findById (id).orElse (null);
    }

    public boolean deleteJobById(Long id) {
        Optional<Job> job = jobRepository.findById(id);
        if (job.isEmpty ()) {
            return false;
        }
        jobRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateJob (Long id, Job updatedJob) {
        Optional<Job> jobOptional=jobRepository.findById (id);

            if(jobOptional.isPresent ()){
                Job job=jobOptional.get ();
                job.setTitle (updatedJob.getTitle ());
                job.setDescription (updatedJob.getDescription ());
                job.setMaxSalary (updatedJob.getMaxSalary ());
                job.setMinSalary (updatedJob.getMinSalary ());
                job.setLocation (updatedJob.getLocation ());
                jobRepository.save (job);
                return true;
            }

        return false;
    }
}
