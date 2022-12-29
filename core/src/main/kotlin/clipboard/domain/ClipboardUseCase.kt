package clipboard.domain

class ClipboardUseCase(
    private val repository: ClipboardRepository
) : ClipboardRepository by repository