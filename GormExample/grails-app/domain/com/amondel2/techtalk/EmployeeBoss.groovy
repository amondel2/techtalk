package com.amondel2.techtalk

import org.apache.commons.lang.builder.HashCodeBuilder
import grails.rest.*

@Resource(uri='/EmployeeBoss', formats=['json', 'xml'])
class EmployeeBoss implements Serializable {

    private static final long serialVersionUID = 1
    def utilService = new Utils()

    String id
    Employees employe
    Employees boss
    Boolean defaultBoss = false

    EmployeeBoss(Employees u, Employees r) {
        this()
        employe = u
        boss = r
    }

    static boolean exists(String employeId, String bossId) {
        EmployeeBoss.where {
            employe == Employees.load(employeId) &&
            boss == Employees.load(bossId)
        }.count()
    }

    @Override
    boolean equals(other) {
        if (!(other instanceof EmployeeBoss)) {
            return false
        }

        other.employe?.id == employe?.id && other.boss?.id == boss?.id
    }

    @Override
    int hashCode() {
        def builder = new HashCodeBuilder()
        if (employe) builder.append(employe.id)
        if (boss) builder.append(boss.id)
        builder.toHashCode()
    }

    static constraints = {
        boss validator: { Employees b, EmployeeBoss eb ->
            if (eb.employe == null || eb.employe.id == null) return
            boolean existing = false
            EmployeeBoss.withNewSession {
                existing = EmployeeBoss.exists(eb.employe.id, b.id)
            }
            if (existing) {
                return 'EmployeeBoss.exists'
            }
        }
        id display:false
    }

    def removeOtherDefaults() {
        if(defaultBoss) {
            def query = EmployeeBoss.where {
                defaultBoss == true
                employe == employe
                boss != boss
            }
            query.updateAll(defaultBoss:false)
        }
    }

    def beforeValidate() {
        if(!id || id.equals(null)) {
            id  = utilService.idGenerator()
        }
    }

    def beforeInsert() {
        if(!id || id.equals(null)) {
            id  = utilService.idGenerator()
        }
    }

    def afterInsert() {
        removeOtherDefaults()
    }

    def afterUpdate() {
        removeOtherDefaults()
    }

    static mapping = {
        version false
        id generator:'assigned'
    }
}