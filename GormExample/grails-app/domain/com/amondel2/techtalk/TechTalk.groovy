package com.amondel2.techtalk

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class TechTalk implements Serializable {

	static constraints = {
		test nullable:false,blank:false,unique:true
		id display:false
	}


	static mapping = {
		table "TechTalk"
		id generator:'assigned'
		version false
		jobs cascade: "all-delete-orphan"
	}

	static hasMany = [jobs:Jobs]
	
	
	public boolean equals(java.lang.Object other) {
		if (other == null) return false
		else if (this.is(other)) return true
		else if (!(other instanceof TechTalk)) return false
		else if (id != other.id) return false
		else if (test != other.test) return false
		else if (bob != other.bob) return false
		return true
	}

	public String toString(){
		return this.test
	}
	
	String test
	String bob
	String id
}