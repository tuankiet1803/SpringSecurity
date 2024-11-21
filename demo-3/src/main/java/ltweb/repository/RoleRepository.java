package ltweb.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import ltweb.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	
}
