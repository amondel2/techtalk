package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*


@EqualsAndHashCode(includes=['id','name','organization'])
@Resource(uri='/jobs', formats=['json', 'xml'])
class Jobs {

    static constraints = {
	name unique: true
	organization nullable:true,blank:false
	id display:false
    }

    static mapping = {
	organaization column: 'organaization_id'
	table "Jobs"
	id generator: 'assigned'
	version false
	employees cascade: "all-delete-orphan"
	organaization cascade: "all"

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
