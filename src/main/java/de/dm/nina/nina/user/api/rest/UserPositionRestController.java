package de.dm.nina.nina.user.api.rest;

import de.dm.nina.nina.user.persistence.UserPosition;
import de.dm.nina.nina.user.persistence.UserPositionRepository;
import io.swagger.annotations.Api;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api
public class UserPositionRestController {
    private static final Logger LOG = LoggerFactory.getLogger(UserPositionRestController.class);

    private final UserPositionRepository userPositionRepository;

    @Autowired
    public UserPositionRestController(UserPositionRepository userPositionRepository){
        this.userPositionRepository = userPositionRepository;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPerson", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<UserPosition> findPerson(@RequestParam String name) {
        LOG.info("Tried to find person with name: {}", name);
        return ResponseEntity.ok(userPositionRepository.getOne(name));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPersons", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<UserPosition>> findPersons(){
        LOG.info("Tried to find all persons.");
        return ResponseEntity.ok(userPositionRepository.findAll());
    }
}
