package com.app.coresamplesourcemvvmkoinkotlin.data.remote

class Resource<T>(val status: Boolean, val data: T?, val error: String?, val code: Int) {

    companion object {

        fun <T> success(data: T?): Resource<T> {
            return Resource(true, data, null, 200)
        }

        fun <T> fail(error: String, code: Int): Resource<T> {
            return Resource(false, null, error, code)
        }
    }


    override fun equals(other: Any?): Boolean {
        if (this === other) {
            return true
        }
        if (other == null || javaClass != other.javaClass) {
            return false
        }

        val resource = other as Resource<*>?

        if (status != resource!!.status) {
            return false
        }
        if (if (error != null) error != resource.error else resource.error != null) {
            return false
        }
        return if (data != null) data == resource.data else resource.data == null
    }

    override fun hashCode(): Int {
        var result = status.hashCode()
        result = 31 * result + (error?.hashCode() ?: 0)
        result = 31 * result + (data?.hashCode() ?: 0)
        return result
    }

    override fun toString(): String {
        return "Resource(status=$status, data=$data, error=$error, code=$code)"
    }


}