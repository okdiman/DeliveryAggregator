package domain

import android.net.Uri

interface LoadImageUseCase : suspend (Uri) -> String