package net.antra.restful.mobileappws.io.repository;

import net.antra.restful.mobileappws.io.entity.UserEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> {
    UserEntity findUserByEmail(String email);

    UserEntity findByUserId(String id);
}
