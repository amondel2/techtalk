package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*


@EqualsAndHashCode(includes=['id'])
@Resource(uri='/jobs', formats=['json', 'xml'])
class Jobs {
    def utilService = new Utils()
    private static final serialVersionUID = 1L

    static constraints = {
        name unique: ['company'],nullable:false,blank:false
        id display:false
    }

    static mapping = {
        id generator: 'assigned'
        version false
        organaization cascade: "none"
        employees cascade: "all-delete-orphan"
    }

    public String toString(){
        return this.name
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

    static hasMany = [orgJobs:OrgJobs]
    static belongsTo = [company:Company]


    Company company
    String name
    String id

}
