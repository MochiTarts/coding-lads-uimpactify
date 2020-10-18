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
import com.utsc.project_coding_lads.enums.RoleEnum;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityAlreadyExistsException;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.exception.InvalidSocialInitNameException;
import com.utsc.project_coding_lads.exception.MissingInformationException;
import com.utsc.project_coding_lads.exception.UserTypeInvalidException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.SocialInitiativeService;
import com.utsc.project_coding_lads.service.UserService;
import com.utsc.project_coding_lads.validator.UserValidator;

@Service
@Transactional
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
		try {
			UserValidator validator = new UserValidator();
			String roleName = null;
			String socialInitName = null;
			if (validator.validate(user)) {
				if ((user.getRole() == null) && (user.getSocialInit() == null)) {
					throw new BadRequestException("userType and userSocialInit cannot both be empty");
				}
				if (user.getRole() != null) {
					roleName = user.getRole().getName();
				}
				if (user.getSocialInit() != null) {
					socialInitName = user.getSocialInit().getName();
				}
				user.setRole(null);
				user.setSocialInit(null);
				
				if (roleName != null) {
					if (roleName.equals(RoleEnum.IMPACT_LEARNER.name())) {
						ImpactLearner learner = new ImpactLearner();
						learner.setUser(user);
						learnerService.storeImpactLearner(learner);
						
						Role userRole = new Role(roleName);
						userRole.setId(roleService.findRoleIdByName(roleName));
						user.setRole(userRole);
					} else if (roleName.equals(RoleEnum.IMPACT_CONSULTANT.name())) {
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
				
				if (socialInitName != null) {
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
				throw new MissingInformationException("Request is missing required info");
			}
		} catch(DataIntegrityViolationException e) {
			throw new EntityAlreadyExistsException("Username already exists");
		} catch(UserTypeInvalidException e) {
			throw e;
		} catch(InvalidSocialInitNameException e) {
			throw e;
		}
	}
	
	@Override
	public User findUserById(Integer id) throws Exception {
		if (!existsById(id)) 
			throw new EntityNotExistException("That user does not exist");
		return userRepo.getOne(id);
	}

	@Override
	public Boolean existsById(Integer id) throws Exception {
		return userRepo.existsById(id);
	}

}