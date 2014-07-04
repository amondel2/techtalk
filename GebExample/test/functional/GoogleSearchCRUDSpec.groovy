import geb.spock.GebReportingSpec

import spock.lang.*

import pages.*

@Stepwise
class GoogleSearchCRUDSpec extends GebReportingSpec {
	
	def "Go to Google"() {
		when:
		to GooglePage
		then:
		searchBox.size() == 1
	}

	def "search for dogs Google"() {
		when:
		search "Dogs"
		then:
		searchResult.size() > 9
	}
}
