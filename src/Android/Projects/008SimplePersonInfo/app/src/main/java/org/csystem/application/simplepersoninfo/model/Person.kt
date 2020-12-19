package org.csystem.application.simplepersoninfo.model

data class Person (val person: String, val citizenId: String, val phone: String)
{
    override fun toString(): String {
        return super.toString()
    }
}