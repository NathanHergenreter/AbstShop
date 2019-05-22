package AbstShop.security;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.web.context.WebApplicationContext;

import AbstShop.entities.User;
import AbstShop.repositories.UserRepository;

@Service
public class ModUserDetailsService implements UserDetailsService {

    @Autowired
    private WebApplicationContext applicationContext;
    private UserRepository userRepository;

    public ModUserDetailsService() { super(); }

    @PostConstruct
    public void completeSetup() {
        userRepository = applicationContext.getBean(UserRepository.class);
    }
    
    @Override
    public UserDetails loadUserByUsername(String username) {
    	User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException(username);
        }
        return new ModUserPrincipal(user);
    }
}
