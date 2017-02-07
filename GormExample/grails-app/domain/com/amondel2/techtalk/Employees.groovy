package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id'])
@Resource(uri='/employees', formats=['json', 'xml'])
class Employees implements Serializable  {

    def utilService = new Utils()
    private static final serialVersionUID = 1L

    static constraints = {
        firstName nullable:false,blank:false
        employeId nullable:false,blank:false,unique:['employeId']
        lastName nullable:false,blank:false
        gender nullable:false,blank:false,inList:['Male', 'Female']
        salary nullable:true,blank:true,validator: { val, obj ->
            if(val && val > 0 && (obj.slaryRate?.trim()?.size() == 0 || !obj.slaryRate)) {
                return ['salaryRateMissing']
            }
        }
        slaryRate nullable:true,blank:true,inList:['Yearly', 'Hourly']
        id display:false
        job nullable:false,blank:false
    }

    static mapping = {
        id generator:'assigned'
        version false
        job cascade: "none"
        boss cascade: "none"
    }

    def beforeValidate() {
        if(!id || id.equals(null)) {
            id  = utilService.idGenerator()
        }
    }

    def beforeInsert() {
        if(!id || id.equals(null)) {
            id  = utilService.idGenerator()
        }
    }

    public String toString(){
        return this.firstName + ' ' + this.lastName
    }

    static belongsTo = [job:OrgJobs,company:Company]
    static hasMany = [bosses:EmployeeBoss,employees:EmployeeBoss]
    static mappedBy = [bosses:'employe',employees:'boss']

    Company company
    String employeId
    String firstName
    String lastName
    String gender
    Float salary
    String slaryRate
    String id
    OrgJobs job
    Boolean mangager = false

}