package com.amondel2.techtalk

import grails.rest.RestfulController
import static org.springframework.http.HttpStatus.*
import static org.springframework.http.HttpMethod.*

class JobsController extends RestfulController {
    static responseFormats = ['json', 'xml']

    def jobService

    JobsController() {
        super(Jobs)
    }

    def getOrgs() {
        if(params?.orgJobId) {
            respond  jobService.getParentOrgs(params?.orgJobId)
        } else {
            respond jobService.getOrgs(params?.id)
        }

    }

    @Override
    protected Jobs createResource() {
        def r =  request.JSON
        Jobs instance = new Jobs(r)
        instance.id = r.id
        instance
    }
}