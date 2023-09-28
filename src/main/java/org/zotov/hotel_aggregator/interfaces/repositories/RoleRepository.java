package org.zotov.hotel_aggregator.interfaces.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.Role;

import java.util.Optional;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {
    public Optional<Role> findByRoleName(String roleName);
}
