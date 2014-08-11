class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

	"/company"(resources:"company")
	"/jobs"(resources:"jobs")
	"/organization"(resources:"organization")
	"/employees"(resources:"employees")
        "/"(view:"/index")
        "500"(view:'/error')
	
	}
}
