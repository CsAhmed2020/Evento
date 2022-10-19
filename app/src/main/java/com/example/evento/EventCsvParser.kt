package com.example.evento



import com.opencsv.CSVReader
import java.io.InputStreamReader

class EventInfoParser  {
    fun parse(file :InputStreamReader): Pair<MutableList<Session>, MutableSet<String>> {
        val dataList = mutableListOf<Session>()
        val days = mutableSetOf<String>()
        //val file = InputStreamReader(assets.open("droidcon.csv"))
        val csvReader = CSVReader(file)
        csvReader.readAll()
            .drop(1)
            .mapNotNull { line ->
                val name = line.getOrNull(1) ?: return@mapNotNull
                val stage = line.getOrNull(2) ?: return@mapNotNull
                val speaker = line.getOrNull(3) ?: return@mapNotNull
                val start = line.getOrNull(4) ?: return@mapNotNull
                val end = line.getOrNull(5) ?: return@mapNotNull
                val day = line.getOrNull(6) ?: return@mapNotNull

                val isSelected = DataStoreUtils.readPreference(key = name , defaultValue = false)

                days.add(day)
                dataList.add(
                    Session(name = name,
                        stage = stage, speaker = speaker, start = start, end = end, day = day, isSelected = isSelected)
                )
            }.also {
                csvReader.close()
            }
        return Pair(dataList,days)
    }
}


