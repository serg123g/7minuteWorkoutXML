package com.example.a7minutesworkoutxml

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.a7minutesworkoutxml.databinding.ActivityExerciseBinding

class ExerciseActivity : AppCompatActivity() {
    private var binding : ActivityExerciseBinding ?= null

    private var restTimer: CountDownTimer ?= null
    private var restProgress = 0

    private var exerciseTimer: CountDownTimer ?= null
    private var exerciseProgress = 0

    private var exerciseList : ArrayList<ExerciseModel> ?= null
    private var currentExercisePosition = -1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityExerciseBinding.inflate(layoutInflater)
        setContentView(binding?.root)

        setSupportActionBar(binding?.toolbarExercise)

        if (supportActionBar != null ){
            supportActionBar?.setDisplayHomeAsUpEnabled(true)
        }

        exerciseList = Constants.defaultExerciseList()

        binding?.toolbarExercise?.setNavigationOnClickListener {
            onBackPressed()
        }

       setUpRestView()
    }

    private fun setUpRestView(){
        binding?.flRestView?.visibility = View.VISIBLE
        binding?.tvTitle?.visibility = View.VISIBLE
        binding?.tvExerciseName?.visibility = View.INVISIBLE
        binding?.flExerciseView?.visibility = View.INVISIBLE
        binding?.ivImage?.visibility = View.INVISIBLE
        binding?.tvTxtUpcomingExercise?.visibility = View.VISIBLE
        binding?.tvUpcomingExercise?.visibility = View.VISIBLE
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        binding?.tvUpcomingExercise?.text = exerciseList!![currentExercisePosition+1].getName()
        setRestProgressBar()
    }



    private fun setUpExerciseView(){

        binding?.flRestView?.visibility = View.INVISIBLE
        binding?.tvTitle?.visibility = View.INVISIBLE
        binding?.tvExerciseName?.visibility = View.VISIBLE
        binding?.flExerciseView?.visibility = View.VISIBLE
        binding?.ivImage?.visibility = View.VISIBLE
        binding?.tvTxtUpcomingExercise?.visibility = View.INVISIBLE
        binding?.tvUpcomingExercise?.visibility = View.INVISIBLE


//        binding?.tvExerciseName?.text = "Exercise Name"
//        binding?.flExerciseView?.visibility = View.VISIBLE

        if (exerciseTimer != null){
            exerciseTimer ?.cancel()
            exerciseProgress = 0
        }
        setExerciseProgressBar()

        binding?.ivImage?.setImageResource(exerciseList!![currentExercisePosition].getImage())
        binding?.tvExerciseName?.text = (exerciseList!![currentExercisePosition].getName())

    }

    private fun setRestProgressBar(){
        binding?.ProgressBar?.progress = restProgress

        restTimer = object : CountDownTimer(10000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.ProgressBar?.progress = 10 - restProgress
                binding?.tvTimer?.text = (10 - restProgress).toString()
            }

            override fun onFinish() {
                currentExercisePosition ++
                setUpExerciseView()
            }

        }.start()
    }

    private fun setExerciseProgressBar(){
        binding?.ProgressBarExercise?.progress = exerciseProgress

       exerciseTimer = object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
                exerciseProgress++
                binding?.ProgressBarExercise?.progress = 30 - exerciseProgress
                binding?.tvTimerExercise?.text = (30 - exerciseProgress).toString()
            }

            override fun onFinish() {
                if (currentExercisePosition <exerciseList?.size!! - 1){
                    setUpRestView()
                } else {
                    Toast.makeText(
                        this@ExerciseActivity,
                        "Congratulations! Workout completed",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }

        }.start()
    }

    private fun startExercise(){
        binding?.tvTitle?.text = "Exercise Name"
        restProgress = 0
        binding?.ProgressBar?.progress = restProgress
        binding?.ivImage?.visibility = View.VISIBLE

        restTimer = object : CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {
                restProgress++
                binding?.ProgressBar?.progress = 30 - restProgress
                binding?.tvTimer?.text = (30 - restProgress).toString()
            }

            override fun onFinish() {
                setUpRestView()
                Toast.makeText(this@ExerciseActivity,
                    "30 seconds timer finished",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }.start()

    }

    override fun onDestroy() {
        super.onDestroy()
        if (restTimer != null){
            restTimer?.cancel()
            restProgress = 0
        }
        if (exerciseTimer != null){
            exerciseTimer ?.cancel()
            exerciseProgress = 0
        }

        binding = null
    }
}