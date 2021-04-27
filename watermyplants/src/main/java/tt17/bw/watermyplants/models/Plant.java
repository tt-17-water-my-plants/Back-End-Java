package tt17.bw.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "plants")
public class Plant extends Auditable
{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long plantid;

    @Column(nullable = false, unique = true)
    private String nickname;

    @Column(nullable = false)
    private String species;

    @Column(nullable = false)
    private String h20frequency;

    @Column
    private String imageurl;

    @ManyToOne
    @JoinColumn(name = "userid",
    nullable = false)
    @JsonIgnoreProperties(value = "plants",
        allowSetters = true)
    private User user;

    public Plant()
    {
    }

    public Plant(
        String nickname,
        String species,
        String h20frequency,
        String imageurl,
        User user)
    {
        this.nickname = nickname;
        this.species = species;
        this.h20frequency = h20frequency;
        this.imageurl = imageurl;
        this.user = user;
    }

    public long getPlantid()
    {
        return plantid;
    }

    public void setPlantid(long plantid)
    {
        this.plantid = plantid;
    }

    public String getNickname()
    {
        return nickname;
    }

    public void setNickname(String nickname)
    {
        this.nickname = nickname;
    }

    public String getSpecies()
    {
        return species;
    }

    public void setSpecies(String species)
    {
        this.species = species;
    }

    public String getH20frequency()
    {
        return h20frequency;
    }

    public void setH20frequency(String h20frequency)
    {
        this.h20frequency = h20frequency;
    }

    public String getImageurl()
    {
        return imageurl;
    }

    public void setImageurl(String imageurl)
    {
        this.imageurl = imageurl;
    }

    public User getUser()
    {
        return user;
    }

    public void setUser(User user)
    {
        this.user = user;
    }
}
