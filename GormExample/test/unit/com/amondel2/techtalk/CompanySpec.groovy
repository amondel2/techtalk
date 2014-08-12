package com.amondel2.techtalk

import grails.test.mixin.TestFor
import spock.lang.Specification
import spock.lang.Unroll

/**
 * See the API for {@link grails.test.mixin.domain.DomainClassUnitTestMixin} for usage instructions
 */
@TestFor(Company)
class CompanySpec extends ValidateableSpec {

    def setup() {
    }

    def cleanup() {
    }

    @Unroll("test Company all constraints '#field' is '#error'")
    def "test Company all constraints"() {
	when:
	def obj = new Company(id: '12389',name:"bob mar")
	def obj2 = new Company(id: '123489',name:"joe mar")
	if(error == 'unique') {
	    obj.putAt(field, val)
	    obj2[field] = val
	} else {
	    obj[field] = val
	}
	then:
	if(error == 'unique') {
	    validateUnqueiConstraints(obj, field, error,[obj,obj2],Company)
	} else {
	    validateConstraints(obj, obj2, field, error,Company)
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
    
    void "test Company to String"() {
	when:
	def obj = new Company(id: '12389',name:"k log")
	then:
	obj.toString() == 'k log'
    }
}
