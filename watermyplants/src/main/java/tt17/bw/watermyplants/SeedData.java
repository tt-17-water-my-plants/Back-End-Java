package tt17.bw.watermyplants;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import tt17.bw.watermyplants.models.Plant;
import tt17.bw.watermyplants.models.Role;
import tt17.bw.watermyplants.models.User;
import tt17.bw.watermyplants.models.UserRoles;
import tt17.bw.watermyplants.repository.PlantRepository;
import tt17.bw.watermyplants.repository.UserRepository;
import tt17.bw.watermyplants.services.PlantService;
import tt17.bw.watermyplants.services.RoleService;
import tt17.bw.watermyplants.services.UserService;

@Transactional
@ConditionalOnProperty(
    prefix = "command.line.runner",
    value = "enabled",
    havingValue = "true",
    matchIfMissing = true)
@Component
public class SeedData implements CommandLineRunner
{
    @Autowired
    PlantRepository plantRepository;

    @Autowired
    UserService userService;

    @Autowired
    RoleService roleService;

    @Autowired
    PlantService plantService;

    @Transactional
    @Override
    public void run(String[] args) throws Exception
    {
        Role r1 = new Role("admin");
        Role r2 = new Role("user");
        Role r3 = new Role("data");

        r1 = roleService.save(r1);
        r2 = roleService.save(r2);
        r3 = roleService.save(r3);

        // admin, data, user
        User u1 = new User("admin",
            "password",
            "2342342345");
        u1.getRoles()
            .add(new UserRoles(u1,
                r1));
        u1.getRoles()
            .add(new UserRoles(u1,
                r2));
        u1.getRoles()
            .add(new UserRoles(u1,
                r3));
        userService.save(u1);

        // data, user
        User u2 = new User("Indy",
            "Dog",
            "2066786789");
        u2.getRoles()
            .add(new UserRoles(u2,
                r2));
        u2.getRoles()
            .add(new UserRoles(u2,
                r3));

        String plant1Nm = "Spike lee";
        Plant plant1 = new Plant(plant1Nm,
            "Opunita",
            "Once a Month",
            null,
            u2);
        u2.getPlants()
            .add(plant1);

        String plant2Nm = "The Queen";
        Plant plant2 = new Plant(plant2Nm,
            "Echeveria, 'fleur Blanc'",
            "every other week",
            null,
            u2);
        u2.getPlants()
            .add(plant2);

        String plant3Nm = "Mad King";
        Plant plant3 = new Plant(plant3Nm,
            "Echeveria 'Doris Taylor",
            "Every other week",
            null,
            u2);
        u2.getPlants()
            .add(plant3);

        userService.save(u2);

        // user
        User u3 = new User("Raul",
            "KingCat",
            "4916099123456");
        u3.getRoles()
            .add(new UserRoles(u3,
                r2));
        userService.save(u3);

//        user
        User u4 = new User("George",
            "ftCat",
            "4259181234");
        u4.getRoles()
            .add(new UserRoles(u4,
                r2));
        userService.save(u4);



        String plant5Nm = "Wiggles";
        Plant plant5 = new Plant(plant5Nm,
            "String of banannas",
            "Every other week",
            null,
            u4);
        u4.getPlants()
            .add(plant5);


        String plant4Nm = "Sad sad";
        Plant plant4 = new Plant(plant4Nm,
            "Monstera",
            "Every week",
            null,
            u3);
        u3.getPlants()
            .add(plant4);

        String plant6Nm = "$Money$";
        Plant plant6 = new Plant(plant6Nm,
            "Jade",
            "Every other week",
            null,
            u3);
        u3.getPlants()
            .add(plant6);

    }
}
