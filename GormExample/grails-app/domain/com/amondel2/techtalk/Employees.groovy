package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id'])
@Resource(uri='/employees', formats=['json', 'xml'])
class Employees implements Serializable  {

    private static final serialVersionUID = 1L
    
    static constraints = {
	firstName nullable:false,blank:false,unique:['lastName']
	lastName nullable:false,blank:false
	gender nullable:false,blank:false,inList:['Male','Female']
	salary nullable:true,blank:true,validator: { val, obj ->
	    if(val && val > 0 && (obj.slaryRate?.trim()?.size() == 0 || !obj.slaryRate)) {
		return ['salaryRateMissing']
	    }
	}
	boss nullable:true,blank:false
	slaryRate nullable:true,blank:true,inList:['Yearly','Hourly']
	id nullable:false,blank:false,unique:true,display:false
	job nullable:true
    }


    static mapping = {
	table "Employees"
	job column: 'job_id'
	boss column: 'boss_id'
	id generator:'assigned'
	version false
	job cascade: "none"
	boss cascade: "none"
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