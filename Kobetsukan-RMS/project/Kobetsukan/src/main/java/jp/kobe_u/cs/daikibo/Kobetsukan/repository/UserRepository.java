package jp.kobe_u.cs.daikibo.Kobetsukan.repository;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;

@Repository
public interface UserRepository extends CrudRepository<User, String>{
    List<User> findAll();
}