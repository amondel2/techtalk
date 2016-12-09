package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id','name'])
@Resource(uri='/company', formats=['json', 'xml'])
class Company implements Serializable  {

    def utilService = new Utils()
    private static final serialVersionUID = 1L

    static constraints = {
        name nullable:false,blank:false,unique:true
        id   display:false
    }

    static mapping = {
        id generator: 'assigned'
        version false
        organizations cascade: "all-delete-orphan"
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
        return this.name
    }

    static hasMany = [organizations:Organization,jobs:Jobs,employees:Employees]

    String id
    String name
}