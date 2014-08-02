package com.amondel2.techtalk

class JobController {
    def jobService
    
    def index() {redirect(action: "list")}
    def list(){
	def  resp = jobService.queryGet("/jobs")
	render(contentType:"text/json"){resp.json}
    }
    
    def parent() {
	def  resp = jobService.queryGet("/jobs/parent/" + params.id)
	render(contentType:"text/json"){jobService.transformResultForJtree(resp.json)}
    }
    
   def update() {
	jobService.update(params)
	render(contentType:"text/json"){["message":"Success"]}
    }
   
   def delete() {
       jobService.deleteQuertStr("/jobs/" + params?.id)
       render(contentType:"text/json"){["message":"Success"]}
   }
}
