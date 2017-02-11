package org.iae.repository;

import java.util.List;

import org.iae.pojo.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository  extends CrudRepository<Project, Long> {

	Page<Project> findAll();
	
	Project findByName(String name);
	
	List<Project> findAllByStatus(String status);
	
}
