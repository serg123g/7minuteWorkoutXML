package com.example.a7minutesworkoutxml

object Constants {

    fun defaultExerciseList(): ArrayList<ExerciseModel>{
        val exerciseList = ArrayList<ExerciseModel>()
        for (i in 0..12 step 1) {
            var id = i
            var name = "${i+1} Exercise"
            var image = when {
                i == 0 -> R.drawable.a
                i == 1 -> R.drawable.a1
                i == 2 -> R.drawable.a2
                i == 3 -> R.drawable.a3
                i == 4 -> R.drawable.a4
                i == 5 -> R.drawable.a5
                i == 6 -> R.drawable.a6
                i == 7 -> R.drawable.a7
                i == 8 -> R.drawable.a8
                i == 9 -> R.drawable.a9
                i == 10 -> R.drawable.b1
                i == 11 -> R.drawable.b2
                else -> R.drawable.a
            }
            var exercise = ExerciseModel(
                id,
                name,
                image
            )
            exerciseList.add(exercise)
        }
//        val firstExercise = ExerciseModel(
//            1,
//            "First Exercise",
//            R.drawable.a,
//        )
//        val secondExercise = ExerciseModel(
//            1,
//            "First Exercise",
//            R.drawable.a,
//        )
//
//        exerciseList.add(firstExercise)


        return exerciseList
    }
}