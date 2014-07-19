package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode(includes=['id','name'])
class Company implements Serializable  {

    static constraints = {
	name nullable:false,blank:false,unique:true
	id display:false
    }

    static mapping = {
	table "Company"
	id generator: 'assigned'
	version false
	organizations cascade: "all-delete-orphan"
    }
    
    public String toString(){
	return this.name
    }

    static hasMany = [organizations:Organization]

    String id
    String name
}