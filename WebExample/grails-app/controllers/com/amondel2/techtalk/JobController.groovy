package com.amondel2.techtalk

class JobController {
    def jobService

    def index(){
	def  resp = jobService.queryGet("/jobs")
	render(contentType:"text/json"){resp.json}
    }

    def parent() {
	def  resp = jobService.queryGet("/jobs/parent/" + params.id)
	render(contentType:"text/json"){jobService.transformResultForJtree(resp.json)}
    }

    def update() {
	def p = request.JSON
	p.id = params.id
	jobService.update(p)
	render(contentType:"text/json"){["message":"Success"]}
    }

    def delete() {
	jobService.deleteQuertStr("/jobs/" + params?.id)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def save() {
	def rtn = jobService.save(request.JSON)
	def status = rtn?.getStatus()
	def result = "FAILURE"
	if(status == 200 || status == 201) {
	    result = "Success"
	}
	def body = rtn?.json
	
	render(contentType:"text/json"){["message":result,results:body,childType: 'employee']}
    }
}
