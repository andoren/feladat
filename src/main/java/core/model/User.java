package core.model;

import core.exceptions.InvalidEmailException;
import core.exceptions.InvalidPassword;
import core.exceptions.InvalidRealnameException;
import core.exceptions.InvalidUsernameException;
import org.hibernate.annotations.Fetch;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

@Entity
@Table(name = "User")
public class User {
    public User() {
    }

    public User(Integer id, String username, String realname, String email,  Role role ) throws InvalidUsernameException, InvalidRealnameException, InvalidEmailException {
        setId(id);
        setRealname(realname);
        setRole(role);
        setUsername(username);
        setEmail(email);
    }
    public User(Integer id, String username, String password,String realname, String email,  Role role  ) throws InvalidPassword, InvalidRealnameException, InvalidUsernameException, InvalidEmailException {
        this(id,username,realname,email,role);
        setPassword(password);

    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "password")
    private String password;
    @Column(name="realname")
    private String realname;
    @Enumerated(value = EnumType.STRING)
    @Column(name="role")
    private Role role;
    @Column(name="email")
    private String email;
    @Column(name="username")
    private String username;
    @Transient
    private String token;
    public void setToken(String token){
        this.token = token;
    }
    public String getToken(){
        return this.token;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) throws InvalidEmailException {
        if (email == null) throw new InvalidEmailException("Email cannot be null.");
        String regex ="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?";
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);
        boolean isMatched = Pattern.matches(regex,email);
        if(!isMatched) throw new InvalidEmailException("Invaild e-mail.");
        this.email = email;
    }
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) throws InvalidUsernameException {
        if(username == null) throw  new InvalidUsernameException("The username cannot be null.");
        else if(username.length() < 5) throw  new InvalidUsernameException("The username must be atleast 5 length long.");
        else if (username.length() >20)throw  new InvalidUsernameException("The username cannot be longer than 20 char.");
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) throws InvalidPassword {
        if (password==null) throw  new InvalidPassword("Password cannot be null.");
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,20}";
        Pattern pattern = Pattern.compile(regex);
        boolean isMatched = Pattern.matches(regex, password);
        if (!isMatched) throw new InvalidPassword("The password must contains atleast one lower case letter, one Upper case letter, a special char, no whitespace allowed, and must be at least 6 char long and max 20 .");
        this.password = password;
    }
    public String getRealname() {
        return realname;
    }
    public void setRealname(String realname) throws InvalidRealnameException {
        if(realname == null )throw new InvalidRealnameException("Realname cannot be null.");
        else if (realname.length() < 4) throw new InvalidRealnameException("Realname is too short.");
        else if (realname.length() > 50)throw new InvalidRealnameException("Realname is too long.");
        else if (!realname.contains(" "))throw new InvalidRealnameException("Realname does not contains space");
        this.realname = realname;
    }
    public Role getRole() {
        return role;
    }
    public void setRole(Role role) {
        this.role = role;
    }
}