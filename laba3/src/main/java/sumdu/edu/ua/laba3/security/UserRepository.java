package sumdu.edu.ua.laba3.security;
import org.springframework.data.jpa.repository.JpaRepository;
import sumdu.edu.ua.laba3.model.User;

/**
 *
 * @author lytvy
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);    
}
