package Com.test.Repo;


import Com.test.Model.user;
import org.springframework.data.jpa.repository.JpaRepository;

public interface userRegistrationRepo extends JpaRepository<user, Integer> {
    user findByName(String username);

    user findById(Long creatorUserId);
    user findById(int creatorUserId);
}
