package tt17.bw.watermyplants.models;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class UserPlantsId implements Serializable
{
    /**
     * The id of the user
     */
    private long user;

    /**
     * The id of the plant
     */
    private long plant;

    /**
     * The default constructor required by JPA
     */
    public UserPlantsId()
    {
    }

    public long getUser()
    {
        return user;
    }

    public void setUser(long user)
    {
        this.user = user;
    }

    public long getPlant()
    {
        return plant;
    }

    public void setPlant(long plant)
    {
        this.plant = plant;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj)
        {
            return true;
        }

        // boolean temp = (o.getClass() instanceof Class);
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        UserPlantsId that = (UserPlantsId) obj;
        return user == that.user && plant == that.plant;
    }

    @Override
    public int hashCode()
    {
        return 37;
    }
}
