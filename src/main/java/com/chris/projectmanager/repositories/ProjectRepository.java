package com.chris.projectmanager.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.chris.projectmanager.models.Project;
import com.chris.projectmanager.models.User;

public interface ProjectRepository extends CrudRepository<Project, Long> {

	List<Project> findAll();
	Project findByIdIs(Long id);
	List<Project> findAllByUsers(User user);
	List<Project> findByUsersNotContains(User user);
}
