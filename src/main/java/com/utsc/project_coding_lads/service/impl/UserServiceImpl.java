package com.utsc.project_coding_lads.service.impl;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enum_role.RolesEnum;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityAlreadyExistsException;
import com.utsc.project_coding_lads.exception.InvalidSocialInitNameException;
import com.utsc.project_coding_lads.exception.MissingRequiredInfoException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.SocialInitiativeService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.service.UserValidator;

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
	@Transactional(rollbackOn = Exception.class)
	public Integer storeUser(User user) throws Exception {
		try {
			UserValidator validator = new UserValidator();
			String roleName = null;
			String socialInitName = null;
			
			Role roleChecker = user.getRole();
			SocialInitiative socialInitChecker = user.getSocialInit();
			user.setRole(null);
			user.setSocialInit(null);
			
			if (validator.validate(user)) {
				
				if ((roleChecker == null) && (socialInitChecker == null)) {
					throw new BadRequestException("role and socialInit cannot both be empty");
				}

				if (roleChecker != null) {
					roleName = roleChecker.getName();
					
					if (roleName != null) {
						if (roleName.equals(RolesEnum.impact_learner.toString())) {
							ImpactLearner learner = new ImpactLearner();
							learner.setUser(user);
							learnerService.storeImpactLearner(learner);
							
							Role userRole = new Role(roleName);
							userRole.setId(roleService.findRoleIdByName(roleName));
							user.setRole(userRole);
						} else if (roleName.equals(RolesEnum.impact_consultant.toString())) {
							ImpactConsultant consultant = new ImpactConsultant();
							consultant.setUser(user);
							consultantService.storeImpactConsultantService(consultant);

							Role userRole = new Role(roleName);
							userRole.setId(roleService.findRoleIdByName(roleName));
							user.setRole(userRole);
						} else {
							throw new UserTypeInvalidException("role name must be of impact_consultant or impact_learner");
						}
					}
				}
				
				if (socialInitChecker != null) {
					socialInitName = socialInitChecker.getName();
					
					SocialInitiative userSocialInit = socialInitService.findSocialInitByName(socialInitName);
					if (userSocialInit != null) {
						user.setSocialInit(userSocialInit);
					} else {
						userSocialInit = new SocialInitiative();
						userSocialInit.setName(socialInitName);
						userSocialInit.setId(socialInitService.storeSocialInit(userSocialInit));
						
						user.setSocialInit(userSocialInit);
					}
				}
					

				return userRepo.save(user).getId();
			} else {
				System.out.println("Got here");
				throw new MissingRequiredInfoException("Request is missing required info");
			}
		} catch(DataIntegrityViolationException e) {
			throw new EntityAlreadyExistsException("Username already exists");
		} catch(UserTypeInvalidException e) {
			throw e;
		} catch(InvalidSocialInitNameException e) {
			throw e;
		}
	}

}