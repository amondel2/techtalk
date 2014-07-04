import com.amondel2.techtalk.Jobs
class BootStrap {

    def init = { servletContext ->
		def j = new Jobs(name:"est")
		j.id  = UUID.randomUUID()?.toString().replaceAll("-", "")
		j.save(flush:true)
		
		j = new Jobs(name:"john")
		j.id  = UUID.randomUUID()?.toString().replaceAll("-", "")
		j.save(flush:true)
    }
    def destroy = {
    }
}
