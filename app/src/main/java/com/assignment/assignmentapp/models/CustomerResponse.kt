package com.assignment.assignmentapp.models

import com.google.gson.annotations.SerializedName

data class CustomerResponse(
    @SerializedName("data") val data : List<Data>
)
