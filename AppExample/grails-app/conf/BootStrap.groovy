import com.amondel2.techtalk.Jobs
import com.amondel2.techtalk.Company
import com.amondel2.techtalk.Employees
import com.amondel2.techtalk.Organization
import com.amondel2.techtalk.EmployeeBoss
import com.amondel2.techtalk.OrgJobs

class BootStrap {

    //    private generateId(obj) {
    //	ArrayList emptyAL = []
    //	generateId(obj,false,emptyAL,emptyAL)
    //    }
    //
    //    private generateId(obj,autoSave) {
    //	ArrayList emptyAL = []
    //	generateId(obj,autoSave,emptyAL, emptyAL)
    //    }
    //
    //    private generateId(obj,autoSave,parentObj,String properyName) {
    //	ArrayList newparentObj = []
    //	ArrayList newProperyName = []
    //	if(parentObj && properyName) {
    //	    newparentObj =  [parentObj]
    //	    newProperyName = [properyName]
    //	}
    //	def isList = newProperyName instanceof ArrayList
    //	generateId(obj,autoSave,newparentObj,newProperyName)
    //    }
    //
    //    private generateId(obj,autoSave,ArrayList parentObj, ArrayList properyName) {
    //	obj.id = UUID.randomUUID()?.toString().replaceAll("-", "")
    //	if(autoSave) {
    //	    obj.save(flush:true)
    //	}
    //	parentObj.eachWithIndex { pobj,idx ->
    //	    def appender = properyName.get(idx)
    //	    pobj."addTo${appender}"(obj)
    //	}
    //	obj
    //    }
    //
    def init = { servletContext ->
        println "Starting DB Bootstap"
        def comp,org1,org2,org3,org4
        try{
            comp = new Company(name:"Verb Animal")
            comp.save(flush:true, failOnError:true)
        } catch ( Exception e) {
            comp = Company.findByName("Verb Animal")
        }
        org1 = new Organization(name:"HR",company:comp)
        org2 = new Organization(name:"IT",company:comp)
        org3 = new Organization(name:"Sales",company:comp)
        org4 = new Organization(name:"Executive",company:comp)
        def subOrg1 = new Organization(name:"Programer",company:comp,parent:org2)
        def subOrg2 = new Organization(name:"QA",company:comp,parent:org2)
        try {
            org1.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            org1 = Organization.findByNameAndCompany("HR",comp)
        }
        try {
            org2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            org2 = Organization.findByNameAndCompany("IT",comp)
        }
        try {
            org3.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            org3 = Organization.findByNameAndCompany("Sales",comp)
        }
        try {
            org4.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            org4 = Organization.findByNameAndCompany("Executive",comp)
        }
        try {
            subOrg1.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            subOrg1 = Organization.findByNameAndCompanyAndParent("Programer",comp,org2)
        }
        try {
            subOrg2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            subOrg2 = Organization.findByNameAndCompanyAndParent("QA",comp,org2)
        }


        //	//IT
        def j1 = new Jobs(name:"Programer",company:comp)
        def j2 = new Jobs(name:"Senior Developer",company:comp)
        def j3 = new Jobs(name:"Testing",company:comp)
        def j4 = new Jobs(name:"Architect",company:comp)

        //	//HR
        def j5 = new Jobs(name:"Compliance",company:comp)
        def j6 = new Jobs(name:"Recruiter",company:comp)
        def j7 = new Jobs(name:"Compensation Analysis",company:comp)
        def j8 = new Jobs(name:"Fun Police",company:comp)
        //	//Sales
        def j9 = new Jobs(name:"Lairs",company:comp)
        def j10 = new Jobs(name:"Senior Lairs",company:comp)
        def j11 = new Jobs(name:"Incompetence",company:comp)
        //	//Executive
        def j12 = new Jobs(name:"CEO",company:comp)
        //All
        def j13 = new Jobs(name:"Manager",company:comp)
        try {
            j1.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j1= Jobs.findByNameAndCompany("Programer",comp)
        }
        try {
            j2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j2 = Jobs.findByNameAndCompany("Senior Developer",comp)
        }
        try {
            j3.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j3 = Jobs.findByNameAndCompany("Testing",comp)
        }
        try {
            j4.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j4 = Jobs.findByNameAndCompany("Architect",comp)
        }
        try {
            j13.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j13 = Jobs.findByNameAndCompany("Manager",comp)
        }
        try {
            j5.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j5 = Jobs.findByNameAndCompany("Compliance",comp)
        }
        try {
            j6.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j6 =  Jobs.findByNameAndCompany("Recruiter",comp)
        }
        try {
            j7.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j7 = Jobs.findByNameAndCompany("Compensation Analysis",comp)
        }
        try {
            j8.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j8 = Jobs.findByNameAndCompany("Fun Police",comp)
        }
        try {
            j9.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j9 = Jobs.findByNameAndCompany("Lairs",comp)
        }
        try {
            j10.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j10 = Jobs.findByNameAndCompany("Senior Lairs",comp)
        }
        try {
            j11.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j11 = Jobs.findByNameAndCompany("Incompetence",comp)
        }
        try {
            j12.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            j12 = Jobs.findByNameAndCompany("CEO",comp)
        }

        //Place Jobs in the orgs
        //IT
        def orgj1 = new OrgJobs(org:subOrg1,job:j1)
        def orgj2 = new OrgJobs(org:subOrg1,job:j2)
        def orgj3 = new OrgJobs(org:subOrg2,job:j3)
        def orgj4 =new OrgJobs(org:subOrg1,job:j4)
        def orgj5 =new OrgJobs(org:org2,job:j13)
        //HR
        def orgj6 =new OrgJobs(org:org1,job:j5)
        def orgj7 =new OrgJobs(org:org1,job:j6)
        def orgj8 =new OrgJobs(org:org1,job:j7)
        def orgj9 =new OrgJobs(org:org1,job:j8)
        def orgj10 =new OrgJobs(org:org1,job:j13)

        //Sales
        def orgj11 =new OrgJobs(org:org3,job:j9)
        def orgj12 =new OrgJobs(org:org3,job:j10)
        def orgj13 =new OrgJobs(org:org3,job:j11)
        def orgj14 =new OrgJobs(org:org3,job:j13)

        //Executive
        def orgj15 =new OrgJobs(org:org4,job:j12)

        try {
            orgj1.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            println e
            orgj1 = OrgJobs.findByOrgAndJob(subOrg1,j1)
        }
        try {
            orgj2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj2 = OrgJobs.findByOrgAndJob(subOrg1,j2)
        }
        try {
            orgj3.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj3 = OrgJobs.findByOrgAndJob(subOrg2,j3)
        }
        try {
            orgj4.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj4 = OrgJobs.findByOrgAndJob(subOrg1,j4)
        }
        try {
            orgj5.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj5 = OrgJobs.findByOrgAndJob(org2,j13)
        }
        try {
            orgj6.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj6 = OrgJobs.findByOrgAndJob(org1,j6)
        }
        try {
            orgj7.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj7 = OrgJobs.findByOrgAndJob(org1,j7)
        }
        try {
            orgj8.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj8= OrgJobs.findByOrgAndJob(org1,j8)
        }
        try {
            orgj9.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj9 = OrgJobs.findByOrgAndJob(org1,j13)
        }
        try {
            orgj10 = orgj10.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj10 = OrgJobs.findByOrgAndJob(org1,j13)
        }
        try {
            orgj11.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj11 =  OrgJobs.findByOrgAndJob(org3,j9)
        }
        try {
            orgj12.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj12 = OrgJobs.findByOrgAndJob(org3,j10)
        }
        try {
            orgj13.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj13 = OrgJobs.findByOrgAndJob(org3,j11)
        }
        try {
            orgj14.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj14 = OrgJobs.findByOrgAndJob(org3,j13)
        }
        try {
            orgj15.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            orgj15 = OrgJobs.findByOrgAndJob(subOrg1,j1)
        }
        //
        //	//Employees
        //	//CEO
        def e1 = new Employees(employeId:"1",firstName:"Marky",lastName:"Mark",gender:"Male",job:orgj15,manager:true,company:comp)
        e1.save(flush:true, failOnError:true)
        try {
            e1.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e1 = Employees.findByEmployeId("1")
        }

        //Managers
        def e2 = new Employees(employeId:"2",firstName:"George",lastName:"Clooney",gender:"Male",job:orgj5,manager:true,company:comp)
        def e3 = new Employees(employeId:"3",firstName:"Marcy",lastName:"Darcy",gender:"Female",job:orgj10,manager:true,company:comp)
        def e4 = new Employees(employeId:"4",firstName:"Jessica",lastName:"Simpson",gender:"Female",job:orgj14,manager:true,company:comp)

        try {
            e2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e2 = Employees.findByEmployeId("2")
        }

        try {
            e3.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e3 = Employees.findByEmployeId("3")
        }

        try {
            e4.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e4 = Employees.findByEmployeId("4")
        }

        def eb1 = new EmployeeBoss(employe:e2,boss:e1,defaultBoss:true)
        //        try{
        eb1.save(flush:true, failOnError:true)
        //        } catch  ( Exception e) {
        //            eb1 = EmployeeBoss.findByEmployeAndBoss(e2,e1)
        //        }

        def eb2 = new EmployeeBoss(employe:e3,boss:e1,defaultBoss:true)
        try{
            eb2.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb2 = EmployeeBoss.findByEmployeAndBoss(e3,e1)
        }

        def eb3 = new EmployeeBoss(employe:e4,boss:e1,defaultBoss:true)
        try{
            eb3.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb3 = EmployeeBoss.findByEmployeAndBoss(e4,e1)
        }

        def e16 = new Employees(employeId:"16",firstName:"Bill",lastName:"Gates",gender:"Male",job:orgj2,company:comp)
        def e5 = new Employees(employeId:"5",firstName:"Steve",lastName:"Wozniak",gender:"Male",job:orgj2,company:comp)
        def e6 = new Employees(employeId:"6",firstName:"Linus",lastName:"Torvalds",gender:"Male",job:orgj2,company:comp)
        def e7 = new Employees(employeId:"7",firstName:"Alan",lastName:"Turing",gender:"Male",job:orgj3,company:comp)
        def e8 = new Employees(employeId:"8",firstName:"Kevin",lastName:"Mitnick",gender:"Male",job:orgj3,company:comp)
        def e9 = new Employees(employeId:"9",firstName:"John",lastName:"Carmack",gender:"Male",job:orgj3,company:comp)
        def e10 = new Employees(employeId:"10",firstName:"Jess",lastName:"Cliffe",gender:"Male",job:orgj4,company:comp)
        def e11 = new Employees(employeId:"11",firstName:"Cliff",lastName:"Bleszinski",gender:"Male",job:orgj4,company:comp)
        def e12 = new Employees(employeId:"12",firstName:"John",lastName:"Romero",gender:"Male",job:orgj4,company:comp)
        def e13 = new Employees(employeId:"13",firstName:"Mackey",lastName:"McCandlish",gender:"Male",job:orgj5,company:comp)
        def e14 = new Employees(employeId:"14",firstName:"Stieg",lastName:"Hedlund",gender:"Male",job:orgj5,company:comp)
        def e15 = new Employees(employeId:"15",firstName:"David",lastName:"Jones",gender:"Male",job:orgj5,company:comp)

        try {
            e16.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e16 = Employees.findByEmployeId("16")
        }
        try {
            e5.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e5 = Employees.findByEmployeId("5")
        }
        try {
            e6.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e6 = Employees.findByEmployeId("6")
        }
        try {
            e7.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e7 = Employees.findByEmployeId("7")
        }
        try {
            e8.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e8 = Employees.findByEmployeId("8")
        }
        try {
            e9.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e9 = Employees.findByEmployeId("9")
        }
        try {
            e10.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e10 = Employees.findByEmployeId("10")
        }
        try {
            e11.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e11 = Employees.findByEmployeId("11")
        }
        try {
            e12.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e12 = Employees.findByEmployeId("12")
        }
        try {
            e13.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e13 = Employees.findByEmployeId("13")
        }
        try {
            e14.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e14 = Employees.findByEmployeId("14")
        }
        try {
            e15.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            e15 = Employees.findByEmployeId("15")
        }

        def eb4 = new EmployeeBoss(employe:e16,boss:e2,defaultBoss:true)
        try{
            eb4.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb4 = EmployeeBoss.findByEmployeAndBoss(e16,e2)
        }

        def eb5 = new EmployeeBoss(employe:e5,boss:e2,defaultBoss:true)
        try{
            eb5.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb5 = EmployeeBoss.findByEmployeAndBoss(e5,e2)
        }

        def eb6 = new EmployeeBoss(employe:e6,boss:e2,defaultBoss:true)
        try{
            eb6.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb6 = EmployeeBoss.findByEmployeAndBoss(e6,e2)
        }
        def eb7 = new EmployeeBoss(employe:e7,boss:e2,defaultBoss:true)
        try{
            eb7.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb7 = EmployeeBoss.findByEmployeAndBoss(e7,e2)
        }

        def eb8 = new EmployeeBoss(employe:e8,boss:e2,defaultBoss:true)
        try{
            eb8.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb8 = EmployeeBoss.findByEmployeAndBoss(e8,e2)
        }
        def eb9 = new EmployeeBoss(employe:e16,boss:e9,defaultBoss:true)
        try{
            eb9.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb9 = EmployeeBoss.findByEmployeAndBoss(e9,e2)
        }

        def eb10 = new EmployeeBoss(employe:e10,boss:e2,defaultBoss:true)
        try{
            eb10.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb10 = EmployeeBoss.findByEmployeAndBoss(e10,e2)
        }

        def eb11 = new EmployeeBoss(employe:e11,boss:e2,defaultBoss:true)
        try{
            eb11.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb11 = EmployeeBoss.findByEmployeAndBoss(e11,e2)
        }

        def eb12 = new EmployeeBoss(employe:e12,boss:e2,defaultBoss:true)
        try{
            eb12.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb12 = EmployeeBoss.findByEmployeAndBoss(e12,e2)
        }

        def eb13 = new EmployeeBoss(employe:e13,boss:e2,defaultBoss:true)
        try{
            eb13.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb13 = EmployeeBoss.findByEmployeAndBoss(e13,e2)
        }

        def eb14 = new EmployeeBoss(employe:e14,boss:e2,defaultBoss:true)
        try{
            eb14.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb14 = EmployeeBoss.findByEmployeAndBoss(e14,e2)
        }

        def eb15 = new EmployeeBoss(employe:e15,boss:e2,defaultBoss:true)
        try{
            eb15.save(flush:true, failOnError:true)
        } catch  ( Exception e) {
            eb15 = EmployeeBoss.findByEmployeAndBoss(e15,e2)
        }

        //	//HR - thank you http://www.imdb.com/list/ls056690043/
        //	generateId(new Employees(firstName:"Britney",lastName:"Spears",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Kim",lastName:"Kardashian",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Paris",lastName:"Hilton",gender:"Female",job:j5,boss:e3),false,[j5,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Lindsay",lastName:"Lohan",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Tara",lastName:"Reid",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Snooki",lastName:"Polizzi",gender:"Female",job:j6,boss:e3),false,[j6,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Alicia",lastName:"Silverstone",gender:"Female",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Christina",lastName:"Aguilera",gender:"Female",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Michael",lastName:"Situation",gender:"Male",job:j7,boss:e3),false,[j7,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Miley",lastName:"Cyrus",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Amanda",lastName:"Bynes",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Justin",lastName:"Bieber",gender:"Female",job:j8,boss:e3),false,[j8,e3],["Employees","DirectReports"])
        //
        //	//Sales - thank you http://wwprintln "Starting DB Bootstap"w.ranker.com/crowdranked-list/lying-politicians-the-worst-liars-in-american-politics
        //	generateId(new Employees(firstName:"Richard",lastName:"Nixon",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"John",lastName:"Edwards",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Barack",lastName:"Obama",gender:"Male",job:j9,boss:e4),false,[j9,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Nancy",lastName:"Pelosi",gender:"Female",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Bill",lastName:"Clinton",gender:"Male",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Joseph",lastName:"McCarthy",gender:"Male",job:j10,boss:e4),false,[j10,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Harry",lastName:"Reid",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"George",lastName:"Bush",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
        //	generateId(new Employees(firstName:"Dick",lastName:"Cheney",gender:"Male",job:j11,boss:e4),false,[j11,e4],["Employees","DirectReports"])
        //
        //	e2.save(flush:true)
        //	e3.save(flush:true)
        //	e4.save(flush:true)
        println "Done Bootstap"
    }
    def destroy = {
    }
}
