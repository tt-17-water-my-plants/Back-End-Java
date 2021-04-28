package tt17.bw.watermyplants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import tt17.bw.watermyplants.exceptions.ResourceNotFoundException;
import tt17.bw.watermyplants.models.Plant;
import tt17.bw.watermyplants.models.User;
import tt17.bw.watermyplants.repository.PlantRepository;

import java.util.ArrayList;
import java.util.List;

@Service("plantService")
public class PlantServiceImpl implements PlantService
{
    @Autowired
    PlantRepository plantrepos;

    @Autowired
    private UserService userService;

    @Override
    public List<Plant> findAllPlants()
    {
        List<Plant> list = new ArrayList<>();

        plantrepos.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public Plant findPlantById(long id)
    {
        return plantrepos.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Plant with id " + id + " Not Found"));
    }

    @Override
    public void delete(long id)
    {
        if (plantrepos.findById(id)
        .isPresent())
        {
            plantrepos.deleteById(id);
        }else
        {
            throw new ResourceNotFoundException("Plant id " + id + " Not Found!");
        }
    }

    @Override
    public Plant update(long plantid, Plant plant)
    {
        Plant currentPlant = plantrepos.findById(plantid)
            .orElseThrow(()-> new ResourceNotFoundException("Plant " + plantid + " Not Found!"));

        if (plant.getNickname() != null)
        {
            currentPlant.setNickname(plant.getNickname());
        }

        if (plant.getSpecies() != null)
        {
            currentPlant.setSpecies(plant.getSpecies());
        }

        if (plant.getH20frequency() != null)
        {
            currentPlant.setH20frequency(plant.getH20frequency());
        }

        if (plant.getImageurl() != null)
        {
            currentPlant.setImageurl(plant.getImageurl());
        }

        return plantrepos.save(currentPlant);
    }

    @Override
    public Plant save(Plant plant)
    {
        Plant newPlant = new Plant();

        if (plant.getPlantid() != 0)
        {
            plantrepos.findById(plant.getPlantid())
                .orElseThrow(()-> new ResourceNotFoundException("Plant id" + plant.getPlantid() + " Not Found!"));
            newPlant.setPlantid(plant.getPlantid());
        }

        newPlant.setNickname(plant.getNickname());
        newPlant.setSpecies(plant.getSpecies());
        newPlant.setH20frequency(plant.getH20frequency());
        newPlant.setImageurl(plant.getImageurl());
        newPlant.setUser(userService.findUserById(plant.getUser().getUserid()));



         return plantrepos.save(newPlant);
    }
}
