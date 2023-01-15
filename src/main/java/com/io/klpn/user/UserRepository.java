package com.io.klpn.user;

import com.io.klpn.user.dtos.UserToAcceptDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    User findByEmail(String email);

    User findEntityById(Long id);

    @Query(value = "SELECT new com.io.klpn.user.dtos.UserToAcceptDTO(u.id, u.firstName, u.lastName, s.indexNumber, s.isAccepted) " +
            "FROM User u INNER JOIN Student s on s.id = u.id WHERE s.isAccepted = false")
    List<UserToAcceptDTO> findUsersToAccept();
}
