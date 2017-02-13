package com.amondel2.techtalk

import grails.transaction.Transactional

@Transactional
class JobService extends BaseService {

    def getOrgs(jobId) {
        OrgJobs.withCriteria{
            eq('job',Job{
                    eq('id',jobId)
                })
        }
    }

    def getParentOrgs(orgJobId) {
        OrgJobs.findById(orgJobId).org
    }
}
