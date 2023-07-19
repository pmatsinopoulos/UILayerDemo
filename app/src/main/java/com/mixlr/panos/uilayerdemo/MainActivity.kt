package com.mixlr.panos.uilayerdemo

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.mixlr.panos.uilayerdemo.databinding.ActivityMainBinding
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {
    private val scoreViewModel: ScoreViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    private val btnHomeGoalClickListener = View.OnClickListener {
        scoreViewModel.homeGoal()
    }

    private val btnGuestGoalClickListener = View.OnClickListener {
        scoreViewModel.guestGoal()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnHomeGoal.setOnClickListener(btnHomeGoalClickListener)
        binding.btnGuestGoal.setOnClickListener(btnGuestGoalClickListener)

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                scoreViewModel.scoreUiState.collect {
                    binding.tvScore.text = it.score
                }
            }
        }
    }
}
