package com.amondel2.techtalk

import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*

@Resource(uri='/OrgJobs', formats=['json', 'xml'])
class OrgJobs implements Serializable {

    private static final long serialVersionUID = 1

    def utilService = new Utils()

    String id
    Organization org
    Jobs job

    static hasMany = [employees:Employees]

    OrgJobs(Organization u, Jobs r) {
        this()
        org = u
        job = r
    }

    static boolean exists(String orgId, String jobId) {
        OrgJobs.where {
            org == Organization.load(orgId) &&
            job == Jobs.load(jobId)
        }.count()
    }

    @Override
    boolean equals(other) {
        if (!(other instanceof OrgJobs)) {
            return false
        }

        other.job?.id == job?.id && other.org?.id == org?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (org) builder.append(org.id)
        if (job) builder.append(job.id)
        builder.toHashCode()
    }

    static constraints = {
        job validator: { Jobs j, OrgJobs oj  ->
            if (oj.org == null || oj.org.id == null) return
            boolean existing = false
            OrgJobs.withNewSession {
                existing = OrgJobs.exists(oj.org.id, j.id)
            }
            if (existing) {
                return 'OrgJobs.exists'
            }
        }
        id display:false
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
        return this.job?.toString() + " - " + this.org?.toString()
    }


    static mapping = {
        version false
        id generator:'assigned'
    }
}