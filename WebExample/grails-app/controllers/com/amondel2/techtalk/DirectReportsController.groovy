package com.amondel2.techtalk

class DirectReportsController extends EmployeeController {

    def employeeService
    
    @Override
    def parent() {
	def resp = employeeService.queryGet("/employees/directReports/" + params.id)
	render(contentType:"text/json"){employeeService.transformResultForJtree(resp.json)}
    }
 
}