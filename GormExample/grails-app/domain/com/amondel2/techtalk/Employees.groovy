package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id','firstName','lastName'])
@Resource(uri='/employees', formats=['json', 'xml'])
class Employees implements Serializable  {

    static constraints = {
	firstName nullable:false,blank:false,unique:['lastName']
	lastName nullable:false,blank:false
	gender nullable:false,blank:false,inList:['Male','Female']
	salary nullable:true,blank:true,validation: { val, obj ->
	    def v = val.trim()
	    if(v && v.size() > 0 && obj.slaryRate?.trim().size() == 0) {
		return ['salaryRateMissing']
	    }
	}
	boss nullable:true,blank:false
	slaryRate nullable:true,blank:true,inList:['Yearly','Hourly']
	id display:false
    }


    static mapping = {
	table "Employees"
	job column: 'job_id'
	boss column: 'boss_id'
	id generator:'assigned'
	version false
	job cascade: "all"
	boss cascade: "all"
	directReports cascade: "all-delete-orphan"
    }

    public String toString(){
	return this.firstName + ' ' + this.lastName
    }

    static belongsTo = [job:Jobs,boss:Employees]
    static hasMany = [directReports:Employees]

    String firstName
    String lastName
    String gender
    Float salary
    String slaryRate
    String id
    Jobs job
    Boolean mangager = false
    Employees boss = null
}