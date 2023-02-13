import com.adeo.kviewmodel.BaseSharedViewModel
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext

abstract class BaseViewModel<State : Any, Action, Event>(initialState: State) :
    BaseSharedViewModel<State, Action, Event>(initialState) {

    inline fun launchJob(
        context: CoroutineContext = EmptyCoroutineContext,
        crossinline onError: (t: Throwable) -> Unit = {},
        crossinline onFinally: () -> Unit = {},
        crossinline job: suspend () -> Unit
    ): Job {
        return viewModelScope.launch(context) {
            try {
                job()
            } catch (t: IOException) {
                onError(t)
            } finally {
                onFinally()
            }
        }
    }

    fun onResetAction() {
        viewAction = null
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}