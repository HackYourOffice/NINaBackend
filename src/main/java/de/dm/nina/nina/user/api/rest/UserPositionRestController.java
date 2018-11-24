package de.dm.nina.nina.user.api.rest;

import de.dm.nina.nina.beacon.persistence.Beacon;
import de.dm.nina.nina.beacon.persistence.BeaconRepository;
import de.dm.nina.nina.user.persistence.UserPosition;
import de.dm.nina.nina.user.persistence.UserPositionRepository;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
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
import java.util.Random;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@CrossOrigin(origins = "*")
@RestController
@Api
@Slf4j
public class UserPositionRestController {
    private final UserPositionRepository userPositionRepository;
    private final BeaconRepository beaconRepository;

    @Autowired
    public UserPositionRestController(UserPositionRepository userPositionRepository, BeaconRepository beaconRepository) {
        this.userPositionRepository = userPositionRepository;
        this.beaconRepository = beaconRepository;

    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPerson", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPosition> findPerson(@RequestParam String name) {
        log.info("Tried to find person with name: {}", name);
        return ResponseEntity.ok(userPositionRepository.getOne(name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPersons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserPosition>> findPersons() {
        log.info("Tried to find all persons.");
        return ResponseEntity.ok(userPositionRepository.findAll());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/publishPosition")
    public void publishPosition(@RequestBody PositionRestRequestDto requestDto) {
        log.info("Request data: id = {}; person = {}", requestDto.getId(), requestDto.getPerson());

        Beacon beacon = beaconRepository.getOne(requestDto.getId());
        Date timestamp = Date.from(Instant.now());

        List<UserPosition> userPositions = userPositionRepository.findAllByName(requestDto.getPerson());

        UserPosition userPosition;

        if (userPositions.isEmpty()) {
            Random random = new Random();
            String color = String.format("#%06x", random.nextInt(0xffffff + 1));

            userPosition = new UserPosition(requestDto.getPerson(), beacon, timestamp, color);
        } else {
            userPosition = userPositions.get(0);

            userPosition.setTimestamp(timestamp);
            userPosition.setBeacon(beacon);
        }

        userPositionRepository.save(userPosition);
    }
}
