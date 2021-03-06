package org.iae.repository;

import org.iae.pojo.SecurityQuestion;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityQuestionsRepository extends CrudRepository<SecurityQuestion, Long> {

	Page<SecurityQuestion> findAll(Pageable pageable);
}
