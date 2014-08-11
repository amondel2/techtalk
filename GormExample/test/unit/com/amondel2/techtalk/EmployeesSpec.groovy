package com.amondel2.techtalk

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Employees)
class EmployeesSpec extends ValidateableSpec {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("test Employees all constraints '#field' is '#error'")
    def "test Employees all constraints"() {
	when:
	def obj = new Employees(id: '12389',firstName:"bob", lastName: "mar",gender:'Male',salary:123.23,slaryRate:'Hourly')
	def obj2 = new Employees(id: '123489',firstName:"joe", lastName: "mar",gender:'Male',salary:123.23,slaryRate:'Hourly')
	if(error == 'unique') {
	    obj.putAt(field, val)
	    obj2[field] = val
	} else {
	    obj[field] = val
	}
	then:
	if(error == 'unique') {
	    validateUnqueiConstraints(obj, field, error,[obj,obj2],Employees)
	} else {
	    validateConstraints(obj, obj2, field, error,Employees)
	}

	where:
	error 		| field 		| val
	'nullable' 	| 'firstName' 		| null
	'nullable' 	| 'id' 			| null
	'blank'		| 'firstName'		| ''
	'blank' 	| 'id' 			| ''
	'unique'	| 'id'			| '1234567890'
	'unique'	| 'firstName'		| 'k'

    }

    def "salaryRateMissing test"(){
	when:
	def obj = new Employees(id: '12389',firstName:"bob", lastName: "mar",gender:'Male',salary:123.23,slaryRate:'')
	def obj2 = new Employees(id: '123489',firstName:"joe", lastName: "mar",gender:'Male',salary:123.23,slaryRate:'Hourly')
	obj.id = '12389'
	obj2.id = '123489'
	then:
	validateConstraints(obj, obj2, 'salary', 'salaryRateMissing',Employees)

    }

    void "test Employees to String"() {
	when:
	def obj = new Employees(id: '12389',firstName:"bob", lastName: "mar",gender:'Male')
	then:
	obj.toString() == 'bob mar'
    }
}
