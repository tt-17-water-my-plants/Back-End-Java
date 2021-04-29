package tt17.bw.watermyplants.repository;

import org.springframework.data.repository.CrudRepository;
import tt17.bw.watermyplants.models.Plant;

public interface PlantRepository extends CrudRepository<Plant, Long>
{
}
