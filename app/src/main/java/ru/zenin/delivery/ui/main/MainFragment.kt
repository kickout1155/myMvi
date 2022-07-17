package ru.zenin.delivery.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.viewbinding.ViewBinding
import dagger.hilt.android.AndroidEntryPoint
import ru.zenin.delivery.R
import ru.zenin.delivery.databinding.FragmentMainBinding
import ru.zenin.delivery.ui.base.BaseMviFragment
import javax.inject.Inject

@AndroidEntryPoint
class MainFragment : BaseMviFragment<
        MainIntent,
        MainViewState,
        MainViewEvent,
        MainViewModel>(R.layout.fragment_main) {

    @Inject
    lateinit var factory: MainViewModelFactory.Factory

    override val viewModel: MainViewModel by viewModels {
        factory.create()
    }

    override val binding: FragmentMainBinding get() = checkNotNull(_binding) as FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return _binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        binding.sendEvent.setOnClickListener {
            viewModel.handleIntent(MainIntent.ClickEvent)
        }

        binding.sendIntent.setOnClickListener {
            viewModel.handleIntent(MainIntent.ClickIntent)
        }
    }

    override fun render(state: MainViewState) {
        binding.textView.text = state.text
    }
}