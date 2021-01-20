package com.backbase.data.remote.model

import com.google.gson.annotations.SerializedName

data class ConfigurationDTO(
    @SerializedName("images") val imageConfiguration: ImageConfigurationDTO,
    @SerializedName("change_keys") val changeKeys: List<String>
)