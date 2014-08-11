package pages

import geb.Page;

import com.amondel2.techtalk.Company;

class WebAppPage extends Page {
    static url = "http://localhost:8092/WebExample/"
    static at = {
	title ==~ /WebApp Example/
    }

    static content = {
	company{ $(this.getCompanyDB().id) }
	searchButton(wait: true){$("#gbqfb")}
	searchResult{ $("div.rc")}
    }
    
    void getCompanyDB() {
	Company.first()
    }
    
   
   

    void rename(String selector,String searchTerm) {
		
    }
}
