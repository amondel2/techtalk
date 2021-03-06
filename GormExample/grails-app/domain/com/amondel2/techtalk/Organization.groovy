package com.amondel2.techtalk
import java.io.Serializable;

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id'])
@Resource(uri='/organization', formats=['json', 'xml'])
class Organization implements Serializable {
    
    private static final serialVersionUID = 1L
    
    static constraints = {
	name nullable:false,blank:false,unique:true
	company nullable:false,blank:false
	id nullable:false,blank:false,unique:true,display:false
    }

    static mapping = {
	table "Organization"
	id generator: 'assigned'
	version false
	jobs cascade: "all-delete-orphan"
	company cascade: "none"
	company column: 'company_id'
    }
       
    static hasMany = [jobs:Jobs]
    static belongsTo = [company:Company]
    
    public String toString(){
	return this.name
    }
    
    String id
    String name
    Company company
}