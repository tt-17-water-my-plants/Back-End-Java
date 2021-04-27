package tt17.bw.watermyplants.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.swagger.readers.operation.ResponseHeaders;
import tt17.bw.watermyplants.models.Plant;
import tt17.bw.watermyplants.services.PlantService;

import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/plants")
public class PlantController
{
    @Autowired
    private PlantService plantService;

    @GetMapping(value = "/plants",
        produces = "application/json")
    public ResponseEntity<?> listAllPlants()
    {
        List<Plant> rtnList = plantService.findAllPlants();
        return new ResponseEntity<>(rtnList, HttpStatus.OK);
    }

    @PostMapping(value = "/plant",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addNewPlant(
        @Valid
        @RequestBody
        Plant newPlant) throws URISyntaxException
    {
        newPlant = plantService.save(newPlant);

        HttpHeaders responseHeaders = new HttpHeaders();
        URI newPlantURI = ServletUriComponentsBuilder.fromCurrentRequest()
            .path("/{plantid}")
            .buildAndExpand(newPlant.getPlantid())
            .toUri();
        responseHeaders.setLocation(newPlantURI);

        return new ResponseEntity<>(null, responseHeaders, HttpStatus.CREATED)
    }
}
