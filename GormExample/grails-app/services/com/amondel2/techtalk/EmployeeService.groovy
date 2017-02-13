package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class EmployeeService extends BaseService {

    def getBosses(empId) {
        EmployeeBoss.withCriteria{
            eq('eemployee',Employee {
                    eq('id', empId)
                })
        }
    }

    def getJob(empId) {
        Employee.findById(empId)?.job
    }

    def getDirectReports(empId) {
        Employees.findById(empId).employees
    }



}
