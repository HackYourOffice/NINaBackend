package de.dm.nina.nina.beacon.api.rest;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api
public class BeaconRestController {
    @RequestMapping(method= RequestMethod.GET, value = "/findPerson", produces = APPLICATION_JSON_VALUE)
    public void publishPostition(){

    }
}
