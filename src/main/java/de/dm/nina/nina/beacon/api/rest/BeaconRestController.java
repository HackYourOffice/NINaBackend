package de.dm.nina.nina.beacon.api.rest;

import de.dm.nina.nina.beacon.persistence.Beacon;
import de.dm.nina.nina.beacon.persistence.BeaconRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Api
public class BeaconRestController {
    private static final Logger LOG = LoggerFactory.getLogger(BeaconRestController.class);
    private final BeaconRepository beaconRepository;

    @Autowired
    public BeaconRestController(BeaconRepository beaconRepository) {
        this.beaconRepository = beaconRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findBeacons")
    public ResponseEntity<List<Beacon>> findBeacons() {
        return ResponseEntity.ok(beaconRepository.findAll());
    }
}
