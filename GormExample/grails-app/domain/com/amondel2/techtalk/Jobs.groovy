package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*


@EqualsAndHashCode(includes=['id','name','organization'])
@Resource(uri='/jobs', formats=['json', 'xml'])
class Jobs {

    static constraints = {
	name unique: true,nullable:false,blank:false
	organization nullable:true,blank:false
	id nullable:false,blank:false,unique:true,display:false
    }

    static mapping = {
	organaization column: 'organaization_id'
	table "Jobs"
	id generator: 'assigned'
	version false
	organaization cascade: "all"
	employees cascade: "all-delete-orphan"
    }

    public String toString(){
	return this.name
    }

    static hasMany = [employees:Employees]

    static belongsTo = [organization:Organization]


    String name
    String id
    Organization organization




}
