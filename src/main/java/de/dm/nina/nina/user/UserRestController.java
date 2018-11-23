package de.dm.nina.nina.user;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@Api
public class UserRestController {
    @RequestMapping(method= RequestMethod.GET, value = "/findPerson", produces = APPLICATION_JSON_VALUE)
    public UserRestDto findPerson(){
        return null;
    }

    @RequestMapping(method = RequestMethod.GET, value = "/findPersons", produces = APPLICATION_JSON_VALUE)
    public List<UserRestDto> findPersons(){
        return null;
    }
}
