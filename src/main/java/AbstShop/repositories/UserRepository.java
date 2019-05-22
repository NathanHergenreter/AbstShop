package AbstShop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import AbstShop.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}
