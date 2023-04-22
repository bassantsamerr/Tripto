package com.example.tripto.utils

import com.example.tripto.model.MainModel
import com.example.tripto.model.PlaceModel
object SampleData {

    private val placeModels= listOf(
        PlaceModel("The Egyptian Museum In Cairo",
            "https://vanillapapers.net/wp-content/uploads/2022/07/museum-featured.jpg",
            "The Egyptian Museum is the oldest archaeological museum in the Middle East, and houses the largest collection of Pharaonic antiquities in the world. The museum displays an extensive collection spanning from the Predynastic Period to the Greco-Roman Era.",
            5.0,
            "Cairo"
        ),
        PlaceModel("The Hanging Church",
            "https://www.cleopatraegypttours.com/wp-content/uploads/2018/08/Mosque-of-Amr-ibn-al-Aas-1200x1090.jpg",
            "Its unusual moniker comes from the fact that it's built on top of the gates of an old Roman fortress. Located within Old Coptic Cairo (Masr al-Qadima) in what was ancient Egypt's Heliopolite Nome, the Hanging Church is just a stone's throw from the famous Babylon Fortress",
            3.0,
            "Cairo"
        ),   PlaceModel(
            "Ben Ezra Synagogue",
            "https://images.memphistours.com/large/519144557_Coptic%20district%20(Ben%20Ezra%20Synagogue),%20Cairo%20Egypt.jpg",
            "According to local folklore, it is located on the site where baby Moses was found. This was the synagogue whose geniza or store room was found in the 19th century to contain a treasure of forgotten, stored-away Hebrew, Aramaic and Judeo-Arabic secular and sacred manuscripts.",
            1.2,
        "Cairo"
        ),   PlaceModel(
            "Amr Ibn Al-Aas Mosque",
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQ5fGXeGXVN4nfJ7LToX2VQMs8Kujg6ijM12g&usqp=CAU",
            "The original structure was the first mosque ever built in Egypt and the whole of Africa. For 600 years, the mosque was also an important center of Islamic learning until Al-Muizz's Al-Azhar Mosque in Islamic Cairo replaced it. Through the twentieth century, it was the fourth largest mosque in the Islamic world.",
            2.0,
            "Cairo"

        ),   PlaceModel("St. Barbara Church",
            "https://dynamic-media-cdn.tripadvisor.com/media/photo-o/1c/4f/32/4e/caption.jpg?w=300&h=300&s=1",
            "While Saint Barbara's Church has been a long-lasting example of ancient Coptic architecture, it resembles the shape of ancient basilicas. It comprises an entrance, a narthex, a long nave, several aisles and three sanctuaries.",
            0.0,
            "Cairo"
        ),

    )

    val collections = listOf(
        MainModel("Recommended Places" , placeModels),
        MainModel("Top 10" , placeModels.reversed()),
        MainModel("Tour Packages" , placeModels.shuffled()),
        MainModel("All Places" , placeModels)

    )
}