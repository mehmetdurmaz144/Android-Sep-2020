package org.csystem.application.simplepersoninfo.model.global

public object personValidityUtil
{
    private fun isValidLenghtEquals(text: String, length: Int) = text.length == length
    private fun isValidLenghtGreater(text: String, length: Int) = text.length >= length

    fun isValidCitizenId(text: String) = isValidLenghtEquals(text, 11)
    fun isInvalidCitizenId(text: String) = !isValidCitizenId(text)
    fun isValidPhone(text: String) = isValidLenghtGreater(text, 10)
    fun isInvalidPhone(text: String) = !isValidPhone(text)

    public fun Validate(name: String, citizendId: String, phone: String) : Boolean
    {
        if (name.isBlank())
            return false

        if (citizendId.isBlank() || isInvalidCitizenId(citizendId))
            return false

        if (phone.isBlank() || isInvalidPhone(phone))
            return false

        return true
    }
}