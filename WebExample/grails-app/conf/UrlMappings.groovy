class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }
	
//	"/$controller/create" { action = [GET:"create"] }
//	"/$controller/edit/$id" { action = [GET:"edit"] }
	"/$controller/jsTree" { (action = "jsTree") }
	"/$controller/$id" { action = [GET:"index", PUT:"update", DELETE:"delete"] }
//	"/$controller" { action = [GET:"list", POST:"save"] }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
