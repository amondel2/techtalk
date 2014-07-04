package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode
import grails.rest.*


@EqualsAndHashCode
@Resource(uri='/jobs', formats=['json', 'xml'])
class Jobs {

    static constraints = {
		name unique: true
		techTalk nullable:true,blank:false
		id display:false
    }
	
	public boolean equals(java.lang.Object other) {
		if (other == null) return false
		else if (this.is(other)) return true
		else if (!(other instanceof Jobs)) return false
		else if (id != other.id) return false
		else if (name != other.name) return false
		return true
	}
	
	static mapping = {
		table "Jobs"
		techTalk column: 'tech_talk_id'
		id generator: 'assigned'
		version false
		techTalk cascade: "all"

	}
	
	static belongsTo = [techTalk:TechTalk]
	
		String name
		String id
		TechTalk techTalk
	
	
}
