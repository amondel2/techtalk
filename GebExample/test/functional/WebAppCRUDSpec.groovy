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

    def "Get Orgs"() {
	when:
	openCompany()
	then:
	organizationItems.size() > 1
    }

    def "Get Jobs"() {
	when:
	openJobs()
	then:
	jobItemsFirst.size() > 1
    }

    def "open Employees"() {
	when:
	openEmployees()
	then:
	employeeItems.size() > 1
    }

    def "remane Company Test"() {
	given:
	def newName = "A better named company"
	when:
	rename(newName)
	then:
	company.text().trim().startsWith(newName) == true
	pageHeading.text() == "Welcome to " + newName

    }

    def "put orginal name back"() {

	when:
	println "cleaning up"
	origName()
	then:
	pageHeading.text() == "Welcome to " + getOrginalCompanyName()
    }
}
