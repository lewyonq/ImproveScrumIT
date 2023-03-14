package com.lewyonq.isit.repository;

import com.lewyonq.isit.model.SurveyAttempt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyAttemptRepository extends JpaRepository<SurveyAttempt, Long> {
}
