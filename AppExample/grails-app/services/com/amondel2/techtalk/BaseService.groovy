package com.amondel2.techtalk

import grails.transaction.Transactional
import com.amondel2.techtalk.Company

@Transactional
class BaseService {

    def generateGuid() {
	return UUID.randomUUID()?.toString().replaceAll("-", "")
    }
    
    def findCompany(ident) {
	Company c = Company.findById(ident)
	c
    }
}
