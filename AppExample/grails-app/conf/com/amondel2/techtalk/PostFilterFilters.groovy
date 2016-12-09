package com.amondel2.techtalk

import com.amondel2.techtalk.Company
import com.amondel2.techtalk.Employees
import com.amondel2.techtalk.Jobs
import com.amondel2.techtalk.Organization
import com.amondel2.techtalk.BaseService

class PostFilterFilters {

    def baseService

    def filters = {
	saveFilter(controller:'*', action:'save') {
	    before = {
                //		def req = request.JSON
                //		request.JSON.id = baseService.generateGuid()
                //		switch(controllerName) {
                //		    case 'organization' :
                //		    request.JSON.company = Company.findById(req.parentId)
                //		    break
                //		    case 'jobs' :
                //		    request.JSON.organization = Organization.findById(req.parentId)
                //		    break
                //		    case 'employees' :
                //		    def parent = Jobs.findById(req.parentId)
                //		    if (parent) {
                //			request.JSON.job = parent
                //		    } else {
                //		    	request.JSON.boss = Employees.findById(req.parentId)
                //		    }
                //		    break
                //
                //		}
	    }
	    after = { Map model ->

	    }
	    afterView = { Exception e ->

	    }
	}
    }
}
