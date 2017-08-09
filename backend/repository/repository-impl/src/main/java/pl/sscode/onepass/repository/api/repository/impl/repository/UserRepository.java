package pl.sscode.onepass.repository.api.repository.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sscode.onepass.repository.api.entities.User;

/**
 * Created by sscode on 2017-06-15.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);

}
