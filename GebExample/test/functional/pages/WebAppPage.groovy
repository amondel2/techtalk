package pages

import geb.Page;

import com.amondel2.techtalk.Company;
import com.amondel2.techtalk.Organization

class WebAppPage extends Page {

    def orginalCompanyName
    
    static url = "http://localhost:8092/WebExample/"
    static at = {
	title ==~ /WebApp Example/
    }

    static content = {
	company(wait: true){ $("div.jstree").find("ul").first().find("li").first() }
	companyButton(wait: true){ $(company).find("i.jstree-ocl") }
	companyRightClick(wait:true) { $(company).find("div").first() }
	organizationItems(wait: true){  $(company).find("ul.jstree-children li")}
	organizationButtons(wait: true){  $(organizationItems).find("i.jstree-ocl")}
	jobItemsFirst(wait: true){ $(organizationItems).find("ul.jstree-children li")}
	jobItemsButtons(wait: true){ $(jobItemsFirst).find("i.jstree-ocl")}
	employeeItems(wait:true){$(jobItemsFirst).find("ul.jstree-children li")}
	jsmenu{$("ul.jstree-contextmenu")}
	jsmenuRename{$(jsmenu).find( "li")[1].find('a')}
	pageHeading{$("h1.ng-binding")}

    }

    void openJobs() {
	organizationButtons[0].click()
	waitFor{
	    jobItemsFirst.size() > 0
	}

    }

    void openEmployees() {
	jobItemsButtons[0].click()
	waitFor{
	    employeeItems.size() > 0
	}
    }

    void openCompany() {
	this.orginalCompanyName = company.text().trim()
	println "company name " + this.orginalCompanyName
	companyButton.click()
	waitFor{
	    organizationItems.size() > 0
	}

    }
    
    void rename(String newName) {
	companyRightClick.jquery.contextmenu()
	waitFor{
	    jsmenu.jquery.css('display') == 'block'
	}
	jsmenuRename.click()
	$("span.jstree-clicked input").value newName
    	//need a blur
	$("span.jstree-clicked input").jquery.blur()
	waitFor{
	    pageHeading.text() == "Welcome to " + newName
	}
    }
}
