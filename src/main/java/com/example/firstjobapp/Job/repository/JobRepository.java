package com.example.firstjobapp.Job.repository;

import com.example.firstjobapp.Job.entity.Job;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JobRepository extends JpaRepository<Job,Long> {
}
