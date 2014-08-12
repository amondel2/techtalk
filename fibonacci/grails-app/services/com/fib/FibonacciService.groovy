package com.fib

import grails.transaction.Transactional

@Transactional
class FibonacciService {

    def fib(index) {
            def start = System.currentTimeMillis()  
            def fibdynamic = FibonacciGroovy.fibDynamic(index)
            def now = System.currentTimeMillis()
            def dynamicElapsed = now - start
            start = System.currentTimeMillis()  
            def fibstatic = FibonacciGroovy.fibStatic(index)
            now = System.currentTimeMillis() 
            def staticElapsed = now - start
	
	return ['dynamic': ['result':fibdynamic,'time':dynamicElapsed],
                'static': ['result':fibstatic,'time':staticElapsed] ]
    }
}
