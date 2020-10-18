package com.utsc.project_coding_lads.service.impl;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.utsc.project_coding_lads.domain.ImpactConsultant;
import com.utsc.project_coding_lads.domain.ImpactLearner;
import com.utsc.project_coding_lads.domain.Role;
import com.utsc.project_coding_lads.domain.SocialInitiative;
import com.utsc.project_coding_lads.domain.User;
import com.utsc.project_coding_lads.enums.RoleEnum;
import com.utsc.project_coding_lads.exception.BadRequestException;
import com.utsc.project_coding_lads.exception.EntityNotExistException;
import com.utsc.project_coding_lads.repository.UserRepository;
import com.utsc.project_coding_lads.service.ImpactConsultantService;
import com.utsc.project_coding_lads.service.ImpactLearnerService;
import com.utsc.project_coding_lads.service.RoleService;
import com.utsc.project_coding_lads.service.SocialInitService;
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
	SocialInitService socialInitService;
	
	final static Logger log = LoggerFactory.getLogger(UserServiceImpl.class);


//	@Override
//	public Integer storeUser(User user) throws Exception {
//		try {
//			UserValidator validator = new UserValidator();
//			String roleName = null;
//			String socialInitName = null;
//			if (validator.validate(user)) {
//				if ((user.getRole() == null) && (user.getSocialInit() == null)) {
//					throw new BadRequestException("userType and userSocialInit cannot both be empty");
//				}
//				if (user.getRole() != null) {
//					roleName = user.getRole().getName();
//				}
//				if (user.getSocialInit() != null) {
//					socialInitName = user.getSocialInit().getName();
//				}
//				user.setRole(null);
//				user.setSocialInit(null);
//				
//				if (roleName != null) {
//					if (roleName.equals(RoleEnum.IMPACT_LEARNER.name())) {
//						ImpactLearner learner = new ImpactLearner();
//						learner.setUser(user);
//						learnerService.storeImpactLearner(learner);
//						
//						Role userRole = new Role(roleName);
//						userRole.setId(roleService.findRoleIdByName(roleName));
//						user.setRole(userRole);
//					} else if (roleName.equals(RoleEnum.IMPACT_CONSULTANT.name())) {
//						ImpactConsultant consultant = new ImpactConsultant();
//						consultant.setUser(user);
//						consultantService.storeImpactConsultantService(consultant);
//
//						Role userRole = new Role(roleName);
//						userRole.setId(roleService.findRoleIdByName(roleName));
//						user.setRole(userRole);
//					} else {
//						throw new UserTypeInvalidException("role name must be of impact_consultant or impact_learner");
//					}
//				}
//				
//				if (socialInitName != null) {
//					SocialInitiative userSocialInit = socialInitService.findSocialInitByName(socialInitName);
//					
//					if (userSocialInit != null) {
//						user.setSocialInit(userSocialInit);
//					} else {
//						userSocialInit = new SocialInitiative();
//						userSocialInit.setName(socialInitName);
//						userSocialInit.setId(socialInitService.storeSocialInit(userSocialInit));
//						
//						user.setSocialInit(userSocialInit);
//					}
//					
//				}
//
//				return userRepo.save(user).getId();
//			} else {
//				System.out.println("Got here");
//				throw new MissingInformationException("Request is missing required info");
//			}
//		} catch(DataIntegrityViolationException e) {
//			throw new EntityAlreadyExistsException("Username already exists");
//		} catch(UserTypeInvalidException e) {
//			throw e;
//		} catch(InvalidSocialInitNameException e) {
//			throw e;
//		}
//	}

	@Override
	public Integer storeUser(User user) throws Exception {
		Integer id = null;
		if (user == null)
			throw new BadRequestException("User cannot be null");
		if (user.getRole() == null)
			throw new BadRequestException("Role cannot be null");
		UserValidator validator = new UserValidator(user);
		validator.validate();
		
		if (user.getSocialInit() != null) {
			String socialInitName = user.getSocialInit().getName();
			if (socialInitName != null) {
				SocialInitiative userSocialInit = socialInitService.findSocialInitByName(socialInitName);
				if (userSocialInit != null) {
					user.setSocialInit(userSocialInit);
				} else {
					userSocialInit = new SocialInitiative();
					userSocialInit.setName(socialInitName);
					SocialInitiative savedSocialInit = socialInitService.storeSocialInit(userSocialInit);
					user.setSocialInit(savedSocialInit);
				}
			}
		}
		
		if (user.getRole() != null) {
			String roleName = user.getRole().getName();
//			Role role = roleService.saveRole(user.getRole());
//			log.info("role: " + role.getName());
			Role userRole = roleService.findRoleByName(roleName);
			user.setRole(userRole);
			User savedUser = userRepo.save(user);
			if (roleName.equals(RoleEnum.IMPACT_LEARNER.name())) {
				ImpactLearner learner = new ImpactLearner();
				learner.setUser(savedUser);
				id = learnerService.storeImpactLearner(learner);
			} else if (roleName.equals(RoleEnum.IMPACT_CONSULTANT.name())) {
				ImpactConsultant consultant = new ImpactConsultant();
				consultant.setUser(savedUser);
				id = consultantService.storeImpactConsultantService(consultant);
			}
		} 
		return id;
	}

	@Override
	public User findUserById(Integer id) throws EntityNotExistException {
		if (!existsById(id))
			throw new EntityNotExistException("That user does not exist");
		return userRepo.getOne(id);
	}

	@Override
	public Boolean existsById(Integer id) {
		return userRepo.existsById(id);
	}

}