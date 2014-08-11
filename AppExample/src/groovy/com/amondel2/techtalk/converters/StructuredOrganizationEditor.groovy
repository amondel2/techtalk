package com.amondel2.techtalk.converters

import java.util.Map;

import com.amondel2.techtalk.Organization
import com.amondel2.techtalk.Company
import com.amondel2.techtalk.BaseService

import org.grails.databinding.converters.AbstractStructuredBindingEditor

class StructuredOrganizationEditor  extends AbstractStructuredBindingEditor<Organization> {

    def baseService
    
    @Override
    public Organization getPropertyValue(Map values) {
	// TODO Auto-generated method stub
	println "New Organization"
	Organization newOrg = new Organization();
	newOrg.name = values.name
	newOrg.id = baseService.generateGuid();
	newOrg.company = Company.findById(values.parentId)
	return newOrg
    }

    
}
