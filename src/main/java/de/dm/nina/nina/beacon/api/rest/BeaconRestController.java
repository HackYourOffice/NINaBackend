package de.dm.nina.nina.beacon.api.rest;

import de.dm.nina.nina.beacon.persistence.Beacon;
import de.dm.nina.nina.beacon.persistence.BeaconRepository;
import de.dm.nina.nina.user.persistence.UserPosition;
import de.dm.nina.nina.user.persistence.UserPositionRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

@RestController
@Api
public class BeaconRestController {
    private static final Logger LOG = LoggerFactory.getLogger(BeaconRestController.class);
    private final BeaconRepository beaconRepository;
    private final UserPositionRepository userPositionRepository;

    @Autowired
    public BeaconRestController(BeaconRepository beaconRepository, UserPositionRepository userPositionRepository) {
        this.beaconRepository = beaconRepository;
        this.userPositionRepository = userPositionRepository;
    }

    @RequestMapping(method= RequestMethod.PUT, value = "/publishPosition")
    public void publishPosition(int id, String person){
        LOG.info("Tried to publish new Position: id={}; name={}", id, person);
        Beacon beacon = beaconRepository.getOne(id);
        Date timestamp = Date.from(Instant.now());

        userPositionRepository.save(new UserPosition(person, beacon, timestamp));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBeacons")
    public ResponseEntity<List<Beacon>> findBeacons(){
        return ResponseEntity.ok(beaconRepository.findAll());
    }
}
