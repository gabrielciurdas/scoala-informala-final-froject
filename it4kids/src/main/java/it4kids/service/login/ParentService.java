package it4kids.service.login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParentService {
	private static final Logger LOGGER = LoggerFactory.getLogger(ParentService.class);
	
	/*@Autowired 
	private ParentAccountDAO parentDAO;
	
	@Autowired 
	private ChildAccountDAO childDAO;
	
	@Autowired
	private JdbcTemplateUserDAO jdbcUserDAO;*/

	/*public void addParent(User primaryParent, User parent) {
		LOGGER.debug("Adding User with Teacher Role and username" + parent.getUserName());
		ParentAccount pa = new ParentAccount();
		int childId =  jdbcUserDAO.getUserId(primaryParent.getUserName());
		
		jdbcUserDAO.add(parent);
		parentDAO.add(parent, childId);
		//parentDAO.add(parent, );
		
	}*/

}
