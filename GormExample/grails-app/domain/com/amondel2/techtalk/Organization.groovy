package com.amondel2.techtalk
import java.io.Serializable;

import groovy.transform.EqualsAndHashCode
import grails.rest.*

@EqualsAndHashCode(includes=['id'])
@Resource(uri='/organization', formats=['json', 'xml'])
class Organization implements Serializable {
    def utilService = new Utils()
    private static final serialVersionUID = 1L

    static constraints = {
        name nullable:false,blank:false,unique:['company','parent']
        company nullable:false,blank:false
        parent  nullable:true,blank:false
        id display:false
    }

    static mapping = {
        version false
        jobs cascade: "all-delete-orphan"
        company cascade: "none"
        parent cascade: "none"
        id generator: 'assigned'
    }

    static hasMany = [orgJobs:OrgJobs,subOrgs:Organization]
    static belongsTo = [company:Company,parent:Organization]

    def beforeDelete() {
        this.subOrgs?.each{
            it.delete()
        }
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

    String id
    String name
    Company company
    Organization parent = null
}