package com.zonebug.debugging.DTO.report

import java.io.Serializable
import java.util.*

data class ReportResponseDTO(

    val reportItemDTO : List<ReportItem>

) {
    data class ReportItem(
        val reportImageDTO: ReportImageDTO,
        val checkListDTO: CheckListDTO,
        val drugListDTO: DrugListDTO
    ) {

        data class ReportImageDTO(
            val image: String,
            val bug: String,
            val date: Date
        )

        data class CheckListDTO(
            val bug: String,
            val checklist: List<String>
        )

        data class DrugListDTO(
            val bug: String,
            val druglist: List<DrugItem>
        ) {
            data class DrugItem(
                val name: String,
                val description: String
            )
        }
    }
}



