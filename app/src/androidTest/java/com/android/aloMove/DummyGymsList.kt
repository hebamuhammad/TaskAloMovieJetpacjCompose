package com.android.aloMove

import com.android.aloMove.moves.domain.Move

object DummyGymsList{
    fun getDummyData() = arrayListOf<Move>(
        Move(id = 0 , title = "UpTown Move" , location = "20 El-Gihad, Mit Akaba, Agouza, Giza Governorate 3754204, Egypt" , true  ) ,
        Move(id = 1, title = "Gold's Move" , location = "61 Syria, Mit Akaba, Agouza, Giza Governorate 3752302, Egypt" , true  ) ,
        Move(id = 2 , title = "Hammer Move" , location ="7 Sphinx Square, Al Huwaiteyah, Agouza, Giza Governorate 3753612, Egypt" , true  ) ,
        Move(id = 3 , title = "I-Enenrgy Move" , location ="22 Gool Gamal, Al Huwaiteyah, Agouza, Giza Governorate 3753620, Egypt" , false  ),)
}







