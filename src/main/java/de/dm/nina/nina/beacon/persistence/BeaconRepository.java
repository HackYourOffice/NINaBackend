package de.dm.nina.nina.beacon.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

public interface BeaconRepository extends JpaRepository<Beacon, Integer> {
}
