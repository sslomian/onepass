package pl.sscode.onepass.repository.api.repository.impl.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.sscode.onepass.repository.api.entities.User;
import pl.sscode.onepass.repository.api.entities.UserPrivData;

import java.util.List;

/**
 * Created by sscode on 2017-07-21.
 */
public interface UserPrivDataRepository extends JpaRepository<UserPrivData, Long> {

    UserPrivData findBySite(String site);
    List<UserPrivData> findByUser(User user);

}
