package com.utsc.project_coding_lads.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enum_role.RolesEnum;
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
	public Integer storeUser(User user, String role, String socialInit) throws Exception {
		Role userRole = new Role(role);
		SocialInitiative userSocialInit = new SocialInitiative();
		
		if (validate(user)) {
			
			if ((role == null || role.trim().isEmpty())
					&& (socialInit == null || socialInit.trim().isEmpty())) {
				throw new BadRequestException("userType and userSocialInit cannot both be empty");
			}
			
			if (role != null && !role.trim().isEmpty()) {
				if (role.equals(RolesEnum.impact_learner.toString())) {
					ImpactLearner learner = new ImpactLearner();
					learner.setUser(user);
					learnerService.storeImpactLearner(learner);
					
					userRole.setId(roleService.findRoleIdByName(role));
					user.setRole(userRole);
				} else if (role.equals(RolesEnum.impact_consultant.toString())) {
					ImpactConsultant consultant = new ImpactConsultant();
					consultant.setUser(user);
					consultantService.storeImpactConsultantService(consultant);

					userRole.setId(roleService.findRoleIdByName(role));
					user.setRole(userRole);
				} else {
					throw new BadRequestException("userType must be of impact_consultant or impact_learner");
				}
			}
			
			if (socialInit != null && !socialInit.trim().isEmpty()) {
				Integer socialInitId = socialInitService.findSocialInitIdByName(socialInit);
				
				if (socialInitId != null) {
					userSocialInit.setName(socialInit);
					userSocialInit.setId(socialInitId);
				} else {
					userSocialInit.setName(socialInit);
					socialInitService.storeSocialInit(userSocialInit);
				}
				
				user.setSocialInit(userSocialInit);
			}

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

}
