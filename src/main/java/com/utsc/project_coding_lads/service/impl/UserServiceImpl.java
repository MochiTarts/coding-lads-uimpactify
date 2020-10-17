package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.SocialInitiativeService;
import com.utsc.project_coding_lads.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	UserRepository userRepo;
	
	@Autowired
	RoleService roleService;
	
	@Autowired
	ImpactConsultantService consultantService;
	
	@Autowired
	ImpactLearnerService learnerService;
	
	@Autowired
	SocialInitiativeService socialInitService;
	
	@Override
	public Integer storeUser(User user) throws Exception {
		Role userRole = user.getRole();
		SocialInitiative userSocialInit = user.getSocialInit();
		user.setRole(null);
		user.setSocialInit(null);
		
		if (validate(user)) {
			
			if ((userRole.getName() == null || userRole.getName().trim().isEmpty())
					&& (userSocialInit.getName() == null || userSocialInit.getName().trim().isEmpty())) {
				throw new BadRequestException("userType and userSocialInit cannot both be empty");
			}
			
			if (userRole.getName() != null && !userRole.getName().trim().isEmpty()) {
				
				if (userRole.getName().equals("impact_learner")) {
					ImpactLearner learner = new ImpactLearner();
					learner.setUser(user);
					learnerService.storeImpactLearner(learner);
					user.setRole(userRole);
				} else if (userRole.getName().equals("impact_consultant")) {
					ImpactConsultant consultant = new ImpactConsultant();
					consultant.setUser(user);
					consultantService.storeImpactConsultantService(consultant);
					user.setRole(userRole);
				} else {
					throw new BadRequestException("userType must be of impact_consultant or impact_learner");
				}
				
			}
			
			if (userSocialInit.getName() != null && !userSocialInit.getName().trim().isEmpty()) {
				System.out.println("Social init");
				Integer socialInitId = socialInitService.findSocialInitIdByName(userSocialInit.getName());
				
				if (socialInitId != null) {
					userSocialInit.setId(socialInitId);
				} else {
					socialInitService.storeSocialInit(userSocialInit);
				}
				
				user.setSocialInit(userSocialInit);
			}
			System.out.println("About to return " + userRole.getName());
			return userRepo.save(user).getId();
		} else {
			throw new BadRequestException("Request is missing required info");
		}
	}
	
	public boolean validate(User user) {
		if (user != null && user.getFirstName() != null && !user.getFirstName().trim().isEmpty()
				&& user.getLastName() != null && !user.getLastName().trim().isEmpty()
				&& user.getUsername() != null && !user.getUsername().trim().isEmpty()
				&& user.getHashedPassword() != null && !user.getHashedPassword().trim().isEmpty() && user.getAge() != null) {
			return true;
		}
		return false;
	}
	
	public void hashPassword(String password) {
		
	}

}
