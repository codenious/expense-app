package in.himanshu.expenseapp.repository;

import in.himanshu.expenseapp.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    public Boolean existsByEmail(String email);

    public Boolean existsByUserName(String username);
}
