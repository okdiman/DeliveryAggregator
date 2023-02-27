package additionalInfo.presentation.viewmodel

import BaseViewModel
import additionalInfo.presentation.AdditionalInfoParameters
import additionalInfo.presentation.mapper.AdditionalInfoUiMapper
import additionalInfo.presentation.viewmodel.model.AdditionalInfoAction
import additionalInfo.presentation.viewmodel.model.AdditionalInfoEvent
import additionalInfo.presentation.viewmodel.model.AdditionalInfoState
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

class AdditionalInfoViewModel(parameters: AdditionalInfoParameters) :
    BaseViewModel<AdditionalInfoState, AdditionalInfoAction, AdditionalInfoEvent>(
        initialState = AdditionalInfoState()
    ), KoinComponent {
    private val mapper by inject<AdditionalInfoUiMapper>()

    init {
        viewState = AdditionalInfoState(mapper.map(parameters))
    }

    override fun obtainEvent(viewEvent: AdditionalInfoEvent) {
        when (viewEvent) {
            AdditionalInfoEvent.OnBackClickEvent -> viewAction =
                AdditionalInfoAction.OpenPreviousScreen
        }
    }
}