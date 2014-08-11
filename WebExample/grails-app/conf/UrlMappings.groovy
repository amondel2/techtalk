class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
	
//	"/$controller/create" { action = [GET:"create"] }
//	"/$controller/edit/$id" { action = [GET:"edit"] }
	"/directReports/$id" { 
	     controller = 'employee'
	     action = [PUT:"update", DELETE:"delete"] 
	 } 
	"/directReports" {
	    controller = 'employee'
	    action = [POST:"save"]
	}
	"/$controller/jsTree" { (action = "jsTree") }
	"/$controller/$id" { action = [GET:"index", PUT:"update", DELETE:"delete"] }
	"/$controller" { action = [POST:"save"] }
//	"/$controller" { action = [GET:"list", POST:"save"] }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
