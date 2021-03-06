package com.amondel2.techtalk

import spock.lang.Specification
import grails.test.mixin.support.*

class ValidateableSpec extends Specification {
    void validateConstraints(obj, obj2,field, error,dObj) {
	mockForConstraintsTests(dObj, [obj2])
	def validated = obj.validate()
	if (error && error != 'valid') {
	    assert !validated
	    assert obj.errors[field]
	    assert error == obj.errors[field]
	} else {
	    assert !obj.errors[field]
	}
    }

    void validateUnqueiConstraints(obj, field, error,objsList,dObj) {
	mockForConstraintsTests(dObj, objsList)
	def v = obj.validate()
	org.junit.Assert.assertFalse obj.validate()
	org.junit.Assert.assertEquals "unique", obj.errors[field]
    }

}
