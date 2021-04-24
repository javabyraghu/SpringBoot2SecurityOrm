package in.nareshit.raghu.service.impl;

import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import in.nareshit.raghu.model.User;
import in.nareshit.raghu.repo.UserRepository;
import in.nareshit.raghu.service.IUserService;

@Service
public class UserServiceImpl 
 implements IUserService, UserDetailsService
{
	@Autowired
	private BCryptPasswordEncoder encoder;
	
	@Autowired
	private UserRepository repo; //HAS-A
	
	public Integer saveUser(User user) {
		//read Register Form Password  and encode
		String encPwd = encoder.encode(user.getUsrPwd());
		//set it back to object
		user.setUsrPwd(encPwd);
		//save into DB
		user = repo.save(user);
		
		return user.getUsrId();
	}
	
	@Override
	public Optional<User> findByEmai(String email) {
		return repo.findByUsrMail(email);
	}
	
	//-----------------------------------//
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException 
	{
		//fetch obj by email
		Optional<User> opt =  repo.findByUsrMail(username);
		if(opt.isEmpty()) {
			throw new UsernameNotFoundException("Not exist");
		} else {
			User user = opt.get();
			return new org.springframework.security.core.userdetails
					.User(
							username, 
							user.getUsrPwd(),
							user.getUsrRoles()
							.stream()
							.map(role->new SimpleGrantedAuthority(role))
							.collect(Collectors.toSet())
							);
		}
	}
	//-----------------------------------//
	
}
