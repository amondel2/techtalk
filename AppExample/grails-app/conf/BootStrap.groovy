import com.amondel2.techtalk.Jobs
import com.amondel2.techtalk.Company
import com.amondel2.techtalk.Employees
import com.amondel2.techtalk.Organization

class BootStrap {

    private generateId(obj) {
	ArrayList emptyAL = []
	generateId(obj,false,emptyAL,emptyAL)
    }

    private generateId(obj,autoSave) {
	ArrayList emptyAL = []
	generateId(obj,autoSave,emptyAL, emptyAL)
    }

    private generateId(obj,autoSave,parentObj,String properyName) {
	ArrayList newparentObj = []
	ArrayList newProperyName = []
	if(parentObj && properyName) {
	    newparentObj =  [parentObj]
	    newProperyName = [properyName]
	}
	def isList = newProperyName instanceof ArrayList
	generateId(obj,autoSave,newparentObj,newProperyName)
    }

    private generateId(obj,autoSave,ArrayList parentObj, ArrayList properyName) {
	obj.id = UUID.randomUUID()?.toString().replaceAll("-", "")
	if(autoSave) {
	    obj.save(flush:true)
	}
	parentObj.eachWithIndex { pobj,idx ->
	    def appender = properyName.get(idx)
	    pobj."addTo${appender}"(obj)
	}
	obj
    }

    def init = { servletContext ->
	println "Starting DB Bootstap"
	def comp = generateId(new Company(name:"Verb Animal"),true)

	def org1 = generateId(new Organization(name:"IT",company:comp),false,comp,"Organizations")
	def org2 = generateId(new Organization(name:"HR",company:comp),false,comp,"Organizations")
	def org3 = generateId(new Organization(name:"Sales",company:comp),false,comp,"Organizations")
	def org4 = generateId(new Organization(name:"Executive",company:comp),false,comp,"Organizations")
	comp.save(flush:true)
	//
	//IT
	def j1 = generateId(new Jobs(name:"Programer",organaization:org1),false,org1,"Jobs")
	def j2 = generateId(new Jobs(name:"Senior Developer",organaization:org1),false,org1,"Jobs")
	def j3 = generateId(new Jobs(name:"QA",organaization:org1),false,org1,"Jobs")
	def j4 = generateId(new Jobs(name:"Architect",organaization:org1),false,org1,"Jobs")
	def j13 = generateId(new Jobs(name:"IT Manager",organaization:org1),false,org1,"Jobs")
	org1.save(flush:true)
	//HR
	def j5 = generateId(new Jobs(name:"Compliance",organaization:org2),false,org2,"Jobs")
	def j6 = generateId(new Jobs(name:"Recruiter",organaization:org2),false,org2,"Jobs")
	def j7 = generateId(new Jobs(name:"Compensation Analysis",organaization:org2),false,org2,"Jobs")
	def j8 = generateId(new Jobs(name:"Fun Police",organaization:org2),false,org2,"Jobs")
	def j14 = generateId(new Jobs(name:"HR Manager",organaization:org2),false,org2,"Jobs")
	org2.save(flush:true)

	//Sales
	def j9 = generateId(new Jobs(name:"Lairs",organaization:org3),false,org3,"Jobs")
	def j10 = generateId(new Jobs(name:"Senior Lairs",organaization:org3),false,org3,"Jobs")
	def j11 = generateId(new Jobs(name:"Incompetence",organaization:org3),false,org3,"Jobs")
	def j15 = generateId(new Jobs(name:"Sales Manager",organaization:org3),false,org3,"Jobs")
	org3.save(flush:true)

	//Executive
	def j12 = generateId(new Jobs(name:"CEO",organaization:org4),false,org4,"Jobs")
	org4.save(flush:true)

	//Employees
	//CEO
	def e1 = generateId(new Employees(firstName:"Marky",lastName:"Mark",gender:"Male",job:j12),false,j12,"Employees")
	j12.save(flush:true)

	//Managers
	def e2 = generateId(new Employees(firstName:"George",lastName:"Clooney",gender:"Male",job:j13,manager:true,boss:e1),false,[j13,e1],["Employees","DirectReports"])
	def e3 = generateId(new Employees(firstName:"Marcy",lastName:"Darcy",gender:"Female",job:j14,manager:true,boss:e1),false,[j14,e1],["Employees","DirectReports"])
	def e4 = generateId(new Employees(firstName:"Jessica",lastName:"Simpson",gender:"Female",job:j15,manager:true,boss:e1),false,[j15,e1],["Employees","DirectReports"])
	e1.save(flush:true)
	j13.save(flush:true)
	j14.save(flush:true)
	j15.save(flush:true)
	//IT
	generateId(new Employees(firstName:"Bill",lastName:"Gates",gender:"Male",job:j1,boss:e2),false,[j1,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Steve",lastName:"Wozniak",gender:"Male",job:j1,boss:e2),false,[j1,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Linus",lastName:"Torvalds",gender:"Male",job:j1,boss:e2),false,[j1,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Alan",lastName:"Turing",gender:"Male",job:j2,boss:e2),false,[j2,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Kevin",lastName:"Mitnick",gender:"Male",job:j2,boss:e2),false,[j2,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"John",lastName:"Carmack",gender:"Male",job:j2,boss:e2),false,[j2,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Jess",lastName:"Cliffe",gender:"Male",job:j3,boss:e2),false,[j3,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Cliff",lastName:"Bleszinski",gender:"Male",job:j3,boss:e2),false,[j3,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"John",lastName:"Romero",gender:"Male",job:j3,boss:e2),false,[j3,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Mackey",lastName:"McCandlish",gender:"Male",job:j4,boss:e2),false,[j4,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Stieg",lastName:"Hedlund",gender:"Male",job:j4,boss:e2),false,[j4,e2],["Employees","DirectReports"])
	generateId(new Employees(firstName:"David",lastName:"Jones",gender:"Male",job:j4,boss:e2),false,[j4,e2],["Employees","DirectReports"])

	//HR - thank you http://www.imdb.com/list/ls056690043/
	generateId(new Employees(firstName:"Britney",lastName:"Spears",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Kim",lastName:"Kardashian",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Paris",lastName:"Hilton",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Lindsay",lastName:"Lohan",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Tara",lastName:"Reid",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Snooki",lastName:"Polizzi",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Alicia",lastName:"Silverstone",gender:"Female",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Christina",lastName:"Aguilera",gender:"Female",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Michael",lastName:"Situation",gender:"Male",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Miley",lastName:"Cyrus",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Amanda",lastName:"Bynes",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Justin",lastName:"Bieber",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])

	//Sales - thank you http://www.ranker.com/crowdranked-list/lying-politicians-the-worst-liars-in-american-politics
	generateId(new Employees(firstName:"Richard",lastName:"Nixon",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"John",lastName:"Edwards",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Barack",lastName:"Obama",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Nancy",lastName:"Pelosi",gender:"Female",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Bill",lastName:"Clinton",gender:"Male",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Joseph",lastName:"McCarthy",gender:"Male",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Harry",lastName:"Reid",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"George",lastName:"Bush",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
	generateId(new Employees(firstName:"Dick",lastName:"Cheney",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
	
	e2.save(flush:true)
	j2.save(flush:true)
	j3.save(flush:true)
	j4.save(flush:true)
		
	e3.save(flush:true)
	j5.save(flush:true)
	j6.save(flush:true)
	j7.save(flush:true)
	j8.save(flush:true)
	
	e4.save(flush:true)
	j9.save(flush:true)
	j10.save(flush:true)
	j11.save(flush:true)

    }
    def destroy = {
    }
}
