package tt17.bw.watermyplants.services;

import tt17.bw.watermyplants.models.UserPhone;

import java.util.List;

public interface UserphoneService
{
    List<UserPhone> findAll();

    UserPhone findUserphoneById(long id);



}
