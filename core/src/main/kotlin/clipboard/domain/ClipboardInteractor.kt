package clipboard.domain

class ClipboardInteractor(
    private val repository: ClipboardRepository
) : ClipboardRepository by repository