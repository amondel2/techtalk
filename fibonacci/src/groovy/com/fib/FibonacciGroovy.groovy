package com.fib

import groovy.transform.CompileStatic

class FibonacciGroovy {

    static BigInteger fibDynamic( Integer index ) {
	    BigInteger result = 0
	    BigInteger prev = 1
	    BigInteger current = 0
	    index.times { 
		result = current + prev
		prev = current
		current = result
	    }
	    result 
	}

    @CompileStatic
    static BigInteger fibStatic( Integer index ) {
    BigInteger result = 0
    BigInteger prev = 1
    BigInteger current = 0
    index.times { 
        result = current + prev
        prev = current
        current = result
    }
    result
}

}
