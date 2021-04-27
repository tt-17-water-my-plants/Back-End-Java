package tt17.bw.watermyplants.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tt17.bw.watermyplants.exceptions.ResourceNotFoundException;
import tt17.bw.watermyplants.models.UserPhone;
import tt17.bw.watermyplants.repository.UserPhoneRepository;

import java.util.ArrayList;
import java.util.List;

@Transactional
@Service(value = "userphoneService")
public class UserphoneServiceImpl implements UserphoneService
{
    @Autowired
    private UserPhoneRepository userPhoneRepository;

    @Autowired
    private UserService userService;

    @Autowired
    private HelperFunctions helperFunctions;


    @Override
    public List<UserPhone> findAll()
    {
        List<UserPhone> list = new ArrayList<>();

        userPhoneRepository.findAll()
            .iterator()
            .forEachRemaining(list::add);
        return list;
    }

    @Override
    public UserPhone findUserphoneById(long id)
    {
        return userPhoneRepository.findById(id)
            .orElseThrow(()-> new ResourceNotFoundException("Userphone with id " + id + " Not Found"));
    }
}
