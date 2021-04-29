package tt17.bw.watermyplants.models;

import javax.persistence.*;

@Entity
@Table(name = "userphones")
public class UserPhone extends Auditable
{

    @Id
    @GeneratedValue(strategy =  GenerationType.AUTO)
    private long userphoneid;

    @Column(nullable = false)
    private String userphone;

    @ManyToOne
    @JoinColumn(name = "userid",
        nullable = false)
    private User user;

    public UserPhone()
    {
    }

    public UserPhone(
        String userphone,
        User user)
    {
        this.userphone = userphone;
        this.user = user;
    }

    public long getUserphoneid()
    {
        return userphoneid;
    }

    public void setUserphoneid(long userphoneid)
    {
        this.userphoneid = userphoneid;
    }

    public String getUserphone()
    {
        return userphone;
    }

    public void setUserphone(String userphone)
    {
        this.userphone = userphone.toLowerCase();
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
