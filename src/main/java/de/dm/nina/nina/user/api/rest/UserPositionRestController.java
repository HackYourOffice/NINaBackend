package de.dm.nina.nina.user.api.rest;

import de.dm.nina.nina.beacon.persistence.Beacon;
import de.dm.nina.nina.beacon.persistence.BeaconRepository;
import de.dm.nina.nina.user.persistence.UserPosition;
import de.dm.nina.nina.user.persistence.UserPositionRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Date;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@Api
public class UserPositionRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserPositionRestController.class);

    private final UserPositionRepository userPositionRepository;
    private final BeaconRepository beaconRepository;

    @Autowired
    public UserPositionRestController(UserPositionRepository userPositionRepository, BeaconRepository beaconRepository) {
        this.userPositionRepository = userPositionRepository;
        this.beaconRepository = beaconRepository;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPerson", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPosition> findPerson(@RequestParam String name) {
        LOG.info("Tried to find person with name: {}", name);
        return ResponseEntity.ok(userPositionRepository.getOne(name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPersons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserPosition>> findPersons() {
        LOG.info("Tried to find all persons.");
        return ResponseEntity.ok(userPositionRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publishPosition")
    public void publishPosition(@RequestBody PositionRestRequestDto requestDto) {
        UserPosition userPosition = userPositionRepository.getOne(requestDto.getPerson());
        if (userPosition != null && !userPosition.getBeacon().getId().equals(requestDto.getId())) {
            LOG.info("Tried to publish new Position: id={}; name={}; beaconID={}", requestDto.getId(), requestDto.getPerson(), requestDto.getId());
        }
        Beacon beacon = beaconRepository.getOne(requestDto.getId());
        Date timestamp = Date.from(Instant.now());

        userPositionRepository.save(new UserPosition(requestDto.getPerson(), beacon, timestamp));
    }
}
