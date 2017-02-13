package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class CompanyService extends BaseService {

    def getSubOrgs(companyId) {
        Organization.withCriteria{
            eq('company',findCompany(companyId))
        }
    }

    def getChildren(companyId) {
        Organization.findAllByCompanyAndParentIsNull(findCompany(companyId))
    }
}
