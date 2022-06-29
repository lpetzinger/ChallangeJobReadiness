package com.example.challangereadness.repository.API.Category

import com.google.gson.annotations.SerializedName

class CategoryEntity {
    @SerializedName("domain_id")
    var domainId: String = ""

    @SerializedName("domain_name")
    var domainName: String = ""

    @SerializedName("category_id")

    var categoryId: String = ""

    @SerializedName("category_name")
    var caregoryName: String = ""

    var attributes = arrayListOf<Any>()
}
