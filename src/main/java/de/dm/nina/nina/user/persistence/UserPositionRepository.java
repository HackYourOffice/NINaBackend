package de.dm.nina.nina.user.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserPositionRepository extends JpaRepository<UserPosition, String> {
    List<UserPosition> findAllByName(String name);
}
