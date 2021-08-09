package com.cms.crypto.repo;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.cms.crypto.entity.BaseRepository;
import com.cms.crypto.entity.User;

@Repository
public interface UserRepo extends BaseRepository<User, Integer> {

	@Query("SELECT u FROM User u WHERE u.userName = ?1")
	User findByUserName(String userName);

}
