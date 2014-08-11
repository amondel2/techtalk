package com.amondel2.techtalk

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Jobs)
class JobsSpec extends ValidateableSpec {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("test Jobs all constraints '#field' is '#error'")
    def "test Jobs all constraints"() {
	when:
	def obj = new Jobs(id: '12389',name:"bob mar")
	def obj2 = new Jobs(id: '123489',name:"joe mar")
	if(error == 'unique') {
	    obj.putAt(field, val)
	    obj2[field] = val
	} else {
	    obj[field] = val
	}
	then:
	if(error == 'unique') {
	    validateUnqueiConstraints(obj, field, error,[obj,obj2],Jobs)
	} else {
	    validateConstraints(obj, obj2, field, error,Jobs)
	}

	where:
	error 		| field 		| val
	'nullable' 	| 'name' 		| null
	'nullable' 	| 'id' 			| null
	'blank'		| 'name'		| ''
	'blank' 	| 'id' 			| ''
	'unique'	| 'id'			| '1234567890'
	'unique'	| 'name'		| 'k log'
    }
    
    void "test Job to String"() {
	when:
	def obj = new Jobs(id: '12389',name:"k log")
	then:
	obj.toString() == 'k log'
    }
}
