package org.zotov.hotel_aggregator.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.zotov.hotel_aggregator.models.Role;

@Repository
public interface RoleRepository extends CrudRepository<Role, Long> {

}
