package AbstShop.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import AbstShop.dto.UserDto;
import AbstShop.entities.User;
import AbstShop.repositories.UserRepository;
import AbstShop.validation.UsernameExistsException;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserRepository repository; 
     
    @Transactional
    public User registerNewUserAccount(UserDto accountDto) 
      throws UsernameExistsException {
         
        if (usernameExist(accountDto.getUsername())) {  
            throw new UsernameExistsException(
              "There is an account with that email adress: "
              +  accountDto.getUsername());
        }
        
        final User user = new User(accountDto.getUsername(), accountDto.getPassword());
        
        return repository.save(user);
    }
    
    public User getUser(String username)
    {
    	return repository.findByUsername(username);
    }
    
    private boolean usernameExist(String username) {
        User user = repository.findByUsername(username);
        if (user != null) {
            return true;
        }
        return false;
    }
}
