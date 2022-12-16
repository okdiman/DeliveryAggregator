import com.adeo.kviewmodel.BaseSharedViewModel
import java.io.IOException
import kotlin.coroutines.CoroutineContext
import kotlin.coroutines.EmptyCoroutineContext
import kotlinx.coroutines.Job
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : Any, Action, Event>(initialState: State) :
    BaseSharedViewModel<State, Action, Event>(initialState) {

    override fun obtainEvent(viewEvent: Event) {
        // nothing
    }

    fun launchJob(
        context: CoroutineContext = EmptyCoroutineContext,
        onError: (t: Throwable) -> Unit = {},
        onFinally: () -> Unit = {},
        job: suspend () -> Unit
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

    override fun onCleared() {
        super.onCleared()
        viewModelScope.coroutineContext.cancel()
    }
}