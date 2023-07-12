import { IdenfyFaceAuthUISettings } from './IdenfyFaceAuthUISettings';

export class IdenfyFaceAuthUIBuilder {
  isLanguageSelectionNeeded: boolean;
  skipOnBoardingView: boolean;

  constructor(isLanguageSelectionNeeded = true, skipOnBoardingView = false) {
    this.isLanguageSelectionNeeded = isLanguageSelectionNeeded;
    this.skipOnBoardingView = skipOnBoardingView;
  }

  withLanguageSelection(
    isLanguageSelectionNeededParam: boolean
  ): IdenfyFaceAuthUIBuilder {
    this.isLanguageSelectionNeeded = isLanguageSelectionNeededParam;
    return this;
  }

  withOnBoardingView(
    isOnBoardingViewNeededParam: boolean
  ): IdenfyFaceAuthUIBuilder {
    this.skipOnBoardingView = !isOnBoardingViewNeededParam;
    return this;
  }

  build(): IdenfyFaceAuthUISettings {
    let idenfyFaceAuthUISettingsV2 = new IdenfyFaceAuthUISettings();
    idenfyFaceAuthUISettingsV2.isLanguageSelectionNeeded =
      this.isLanguageSelectionNeeded;
    idenfyFaceAuthUISettingsV2.skipOnBoardingView = this.skipOnBoardingView;
    return idenfyFaceAuthUISettingsV2;
  }
}
