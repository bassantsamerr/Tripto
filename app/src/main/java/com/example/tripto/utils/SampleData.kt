package com.example.tripto.utils

import com.example.tripto.model.ActivityModel
import com.example.tripto.model.MainModel
import com.example.tripto.model.PlaceModel
import com.google.gson.Gson
import java.net.URL

object SampleData {
    private val activities = listOf(
        ActivityModel(
            "horseRiding",
            "https://www.horseriding-hurghada-egypt.com/wp-content/uploads/2018/12/girl2horsesswimming-1600x900.png",
            "horse Riding description",
            3.0,
            "Cairo"
        )
    )
    private val placeModels = listOf(
        PlaceModel(
            1,
            "The Egyptian Museum In Cairo",
            "The Egyptian Museum is the oldest archaeological museum in the Middle East, and houses the largest collection of Pharaonic antiquities in the world. The museum displays an extensive collection spanning from the Predynastic Period to the Greco-Roman Era.",
            "Cairo",
            "https://tinyurl.com/yffcchwp",
            4.5,
            "https://goo.gl/maps/RBKxVzj3sa6zpFcL9",
            30.04846529,
            31.23365667,
            activities.toTypedArray()
        ),PlaceModel(
            2,
            "The Hanging Church",
            "Its unusual moniker comes from the fact that it's built on top of the gates of an old Roman fortress. Located within Old Coptic Cairo (Masr al-Qadima) in what was ancient Egypt's Heliopolite Nome, the Hanging Church is just a stone's throw from the famous Babylon Fortress",
            "Cairo",
            "https://tinyurl.com/2p82vznx",
            4.6,
            "https://goo.gl/maps/xuWFMckZMCXia6uG7",
            30.0053777913529,
            31.2301825999999,
            activities.toTypedArray()
        ),
        )

    val collections = listOf(
        MainModel("Recommended Places", placeModels),
        MainModel("Top 10", placeModels.reversed()),
        MainModel("Tour Packages", placeModels.shuffled()),
        MainModel("All Places", placeModels)
    )

}