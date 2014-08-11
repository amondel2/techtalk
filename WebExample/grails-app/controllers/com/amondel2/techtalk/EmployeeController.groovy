package com.amondel2.techtalk

class EmployeeController {

    def employeeService


    def parent() {

	def  resp = employeeService.queryGet("/employees/parent/" + params.id)
	render(contentType:"text/json"){employeeService.transformResultForJtree(resp.json)}
    }

    def update() {
	def p = request.JSON
	p.id = params.id
	employeeService.update(p)
	render(contentType:"text/json"){["message":"Success"]}
    }

    def delete() {
	employeeService.deleteQuertStr("/employees/" + params?.id)
	render(contentType:"text/json"){["message":"Success"]}
    }
    
    def save() {
	def rtn = employeeService.save(request.JSON)
	def status = rtn?.getStatus()
	def result = "FAILURE"
	if(status == 200 || status == 201) {
	    result = "Success"
	}
	def body = rtn?.json
	
	render(contentType:"text/json"){["message":result,results:body,childType: 'directReports']}
    }
}
