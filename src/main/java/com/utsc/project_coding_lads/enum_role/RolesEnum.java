package com.utsc.project_coding_lads.enum_role;

public enum RolesEnum {
	impact_learner,
	impact_consultant;
	
	@Override
	public String toString() {
		switch(this) {
			case impact_learner:
				return "impact_learner";
			case impact_consultant:
				return "impact_consultant";
			default:
				return null;
		}
	}
	
}
