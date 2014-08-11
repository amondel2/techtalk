import geb.spock.GebReportingSpec
import spock.lang.*
import pages.*

@Stepwise
class WebAppCRUDSpec  extends GebReportingSpec {
    def "Go to WebApp"() {
	when:
	to WebAppPage
    	then:
	  1 == 1
	
    }
    
    def "Get Title"() {
	when:
	def cname = getCompanyDB().name
	wiatFor{
	    company.text == name
	}
	then:
	1 == 1
    }

}
