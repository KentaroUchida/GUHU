package jp.kobe_u.cs.daikibo.Kobetsukan.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import jp.kobe_u.cs.daikibo.Kobetsukan.entity.User;

/**
 * ユーザリポジトリ
 */
@Repository
public interface UserRepository extends CrudRepository<User, String> {
    
}