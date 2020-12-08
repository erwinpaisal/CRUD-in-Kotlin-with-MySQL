package id.erwinpaisal.mentoringweek4.model.action

import com.google.gson.annotations.SerializedName

data class ActionResponse(

        @field:SerializedName("isSucess")
        val isSucess: Boolean? = null,

        @field:SerializedName("message")
        val message: String? = null
)
