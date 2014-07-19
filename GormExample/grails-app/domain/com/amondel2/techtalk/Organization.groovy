package com.amondel2.techtalk
import java.io.Serializable;

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes=['id'])
class Organization implements Serializable {
     
    static constraints = {
	name nullable:false,blank:false,unique:['company']
	company nullable:false,blank:false
	id display:false
    }

    static mapping = {
	table "Organization"
	id generator: 'assigned'
	version false
	jobs cascade: "all-delete-orphan"
	company cascade: "all"
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
