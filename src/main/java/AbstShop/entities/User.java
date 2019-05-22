package AbstShop.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class User {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
 
    @Column(nullable = false, unique = true)
    private String username;
 
    private String password;
    
    private boolean admin; // User = false, Admin = true
    
    protected User() { }
    
    public User(String username, String password)
    {
    	this.username = username;
    	this.password = password;
    	this.admin = false;
    }
    
    public Long id() { return id; }
    public String username() { return username; }
    public String password() { return password; }
    public void setPassword(String password) { this.password = password; }
    public boolean admin() { return admin; }
    public void setAdmin(boolean admin) { this.admin = admin; }
}
