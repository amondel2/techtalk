package pages;

import geb.Page;

class GooglePage extends Page {
	static url = "http://google.com"
	static at = {
		title ==~ /Google/
	}
	
	static content = {
		searchBox{ $("input", name: "q") }
		searchButton(wait: true){$("#gbqfb")}
		searchResult{ $("div.rc")}
	}
	
	void search(String searchTerm) {
		searchBox.value searchTerm
		searchButton.click()
		waitFor{
			title.contains(searchTerm)
		}
		waitFor{
			searchResult.size() > 0
		}
	}
}	