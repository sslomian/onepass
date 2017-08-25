package pl.sscode.onepass.repository.api.repository.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sscode.onepass.repository.api.entities.Authority;

/**
 * Created by sscode on 2017-08-25.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
