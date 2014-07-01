package com.amondel2.techtalk

import java.io.Serializable;

import groovy.transform.EqualsAndHashCode

@EqualsAndHashCode
class Table2 implements Serializable {

    static constraints = {
		name unique: true
		techTalk nullable:true,blank:false
		id display:false
	}

	static mapping = {
		table "Table2"
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