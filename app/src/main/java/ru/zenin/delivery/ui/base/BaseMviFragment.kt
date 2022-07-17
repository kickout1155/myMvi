package ru.zenin.delivery.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.CallSuper
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.flowWithLifecycle
import androidx.lifecycle.lifecycleScope
import androidx.viewbinding.ViewBinding
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import ru.zenin.delivery.R
import ru.zenin.mvicore.Intent
import ru.zenin.mvicore.ViewEvent
import ru.zenin.mvicore.ViewState

abstract class BaseMviFragment<
        INTENT : Intent,
        VIEW_STATE : ViewState,
        VIEW_EVENT : ViewEvent,
        VIEWMODEL : BaseMviViewModel<VIEW_EVENT, VIEW_STATE, INTENT, *, *, *>
        >(
    @LayoutRes layoutId: Int
) : Fragment(layoutId) {

    abstract val viewModel: VIEWMODEL
    protected var _binding: ViewBinding? = null
    protected abstract val binding: ViewBinding

    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        lifecycleScope.launch {
            viewModel.state
                .flowWithLifecycle(lifecycle, Lifecycle.State.STARTED)
                .onEach { render(it) }
                .launchIn(this)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    abstract fun render(state: VIEW_STATE)


}