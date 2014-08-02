package com.amondel2.techtalk

class DirectReportsController {

    def employeeService
    
    def index() { }
    
    def parent() {
	def resp = employeeService.queryGet("/employees/directReports/" + params.id)
	render(contentType:"text/json"){employeeService.transformResultForJtree(resp.json)}
    }
    
    def update() {
	employeeService.update(params)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def delete() {
	employeeService.deleteQuertStr("/employees/" + params?.id)
	render(contentType:"text/json"){["message":"Success"]}
    }
}
