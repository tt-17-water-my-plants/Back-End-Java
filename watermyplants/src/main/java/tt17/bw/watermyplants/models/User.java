package tt17.bw.watermyplants.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The entity allowing interaction with the users table
 */
@Entity
@Table(name = "users")
public class User extends Auditable
{
    /**
     * The primary key (long) of the users table.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long userid;

    /**
     * The username (String). Cannot be null and must be unique
     */
    @Column(nullable = false)
    private String username;

    /**
     * The password (String) for this user. Cannot be null. Never get displayed
     */
    @Column(nullable = false)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    /**
     * Primary email account of user. Could be used as the userid. Cannot be null and must be unique.
     */
    @Column(nullable = false)
    private String primaryphone;

    /**
     * A list of emails for this user
     */
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private List<UserPhone> userphones = new ArrayList<>();

    /**
     * Part of the join relationship between user and role
     * connects users to the user role combination
     */
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private Set<UserRoles> roles = new HashSet<>();

    /**
     * Part of the join relationship between user and plant
     * connects users to the user plants combination
     */
    @OneToMany(mappedBy = "user",
        cascade = CascadeType.ALL,
        orphanRemoval = true)
    @JsonIgnoreProperties(value = "user",
        allowSetters = true)
    private List<Plant> plants = new ArrayList<>();

    /**
     * Default constructor used primarily by the JPA.
     */
    public User()
    {
    }

    /**
     * Given the params, create a new user object
     * <p>
     * userid is autogenerated
     *
     * @param username     The username (String) of the user
     * @param password     The password (String) of the user
     * @param primaryphone The primary phone (String) of the user
     */
    public User(
        String username,
        String password,
        String primaryphone)
    {
        setUsername(username);
        setPassword(password);
        this.primaryphone = primaryphone;
    }

    /**
     * Getter for userid
     *
     * @return the userid (long) of the user
     */
    public long getUserid()
    {
        return userid;
    }

    /**
     * Setter for userid. Used primary for seeding data
     *
     * @param userid the new userid (long) of the user
     */
    public void setUserid(long userid)
    {
        this.userid = userid;
    }

    /**
     * Getter for username
     *
     * @return the username (String) lowercase
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * setter for username
     *
     * @param username the new username (String) converted to lowercase
     */
    public void setUsername(String username)
    {
        this.username = username.toLowerCase();
    }

    /**
     * getter for primary email
     *
     * @return the primary email (String) for the user converted to lowercase
     */
    public String getPrimaryphone()
    {
        return primaryphone;
    }

    /**
     * setter for primary email
     *
     * @param primaryphone the new primary email (String) for the user converted to lowercase
     */
    public void setPrimaryphone(String primaryphone)
    {
        this.primaryphone = primaryphone;
    }

    /**
     * Getter for the password
     *
     * @return the password (String) of the user
     */
    public String getPassword()
    {
        return password;
    }

    /**
     * Setter for password to be used internally, after the password has already been encrypted
     *
     * @param password the new password (String) for the user. Comes in encrypted and stays that way
     */
    public void setPasswordNoEncrypt(String password)
    {
        this.password = password;
    }

    /**
     * @param password the new password (String) for this user. Comes in plain text and goes out encrypted
     */
    public void setPassword(String password)
    {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        this.password = passwordEncoder.encode(password);
    }

    /**
     * Getter for the list of userphones for this user
     *
     * @return the list of userphones (List(Userphones)) for this user
     */
    public List<UserPhone> getUserphones()
    {
        return userphones;
    }

    /**
     * Setter for list of userphones for this user
     *
     * @param userphones the new list of userphones (List(Userphones)) for this user
     */
    public void setUserphones(List<UserPhone> userphones)
    {
        this.userphones = userphones;
    }

    /**
     * Getter for user role combinations
     *
     * @return A list of user role combinations associated with this user
     */
    public Set<UserRoles> getRoles()
    {
        return roles;
    }

    /**
     * Setter for user role combinations
     *
     * @param roles Change the list of user role combinations associated with this user to this one
     */
    public void setRoles(Set<UserRoles> roles)
    {
        this.roles = roles;
    }

    /**
     * Internally, user security requires a list of authorities, roles, that the user has. This method is a simple way to provide those.
     * Note that SimpleGrantedAuthority requests the format ROLE_role name all in capital letters!
     *
     * @return The list of authorities, roles, this user object has
     */
    @JsonIgnore
    public List<SimpleGrantedAuthority> getAuthority()
    {
        List<SimpleGrantedAuthority> rtnList = new ArrayList<>();

        for (UserRoles r : this.roles)
        {
            String myRole = "ROLE_" + r.getRole()
                .getName()
                .toUpperCase();
            rtnList.add(new SimpleGrantedAuthority(myRole));
        }

        return rtnList;
    }

    public List<Plant> getPlants()
    {
        return plants;
    }

    public void setPlants(List<Plant> plants)
    {
        this.plants = plants;
    }
}
