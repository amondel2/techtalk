package com.fib

import grails.converters.JSON

class FibonacciController {

    def fibonacciService

    def index() { 
        def fibNumber = params.id as Integer
        JSON json = fibonacciService.fib(fibNumber) as JSON
	json.prettyPrint = true
        json.render response
    }
}
