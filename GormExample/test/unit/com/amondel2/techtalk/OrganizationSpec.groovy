package com.amondel2.techtalk

import grails.test.mixin.TestMixin
import grails.test.mixin.support.GrailsUnitTestMixin
import spock.lang.Specification
import spock.lang.Unroll
/**
 * See the API for {@link grails.test.mixin.support.GrailsUnitTestMixin} for usage instructions
 */
@TestFor(Organization)
class OrganizationSpec extends ValidateableSpec {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("test Organization all constraints '#field' is '#error'")
    def "test Organization all constraints"() {
	when:
	def obj = new Organization(id: '12389',name:"bob mar")
	def obj2 = new Organization(id: '123489',name:"joe mar")
	if(error == 'unique') {
	    obj.putAt(field, val)
	    obj2[field] = val
	} else {
	    obj[field] = val
	}
	then:
	if(error == 'unique') {
	    validateUnqueiConstraints(obj, field, error,[obj,obj2],Organization)
	} else {
	    validateConstraints(obj, obj2, field, error,Organization)
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
    
    void "test Organization to String"() {
	when:
	def obj = new Organization(id: '12389',name:"k log")
	then:
	obj.toString() == 'k log'
    }
}
