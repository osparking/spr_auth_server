package space.bumtiger.auth.users;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository 
			extends CrudRepository<User, Integer> {
	User findByUsername(String username);
}
