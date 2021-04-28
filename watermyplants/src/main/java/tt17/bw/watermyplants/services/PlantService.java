package tt17.bw.watermyplants.services;

import org.springframework.security.access.method.P;
import tt17.bw.watermyplants.models.Plant;

import java.util.List;

public interface PlantService
{
    List<Plant> findAllPlants();

    Plant findPlantById(long id);

    /**
     * Deletes the user record and its useremail items from the database based off of the provided primary key
     *
     * @param id id The primary key (long) of the user you seek.
     */
    void delete(long id);

    Plant update(long plantid,Plant plant);

    Plant save(Plant plant);
}
