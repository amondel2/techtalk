package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id','name'])
@Resource(uri='/company', formats=['json', 'xml'])
class Company implements Serializable  {
    
    private static final serialVersionUID = 1L

    static constraints = {
	name nullable:false,blank:false,unique:true
	id   nullable:false,blank:false,unique:true,display:false
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