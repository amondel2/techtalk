package com.amondel2.techtalk

class successText  {
    Integer getStatus(){return 200}
    def json = "json"
}

class successText2  {
    Integer getStatus() {return 201}
    def json = "json"
}
class failureText {
    Integer getStatus() {return 404}
    def json = "json"
}

class nullStatus {
    def getStatus() {return null}
    def json = "json"
}

class ResponseClass {
    static successText =  new successText()
    
    static successText2 =  new successText2()
    static failureText =  new failureText()
    
    static nullStatus = new nullStatus()
}
