import Foundation
import iDenfySDK

@objc(IdenfyReactNative)
class IdenfyReactNative: NSObject {

    @objc func start(_ config: NSDictionary,
                     resolver resolve: @escaping RCTPromiseResolveBlock,
                     rejecter reject: @escaping RCTPromiseRejectBlock) {
        DispatchQueue.main.async {
            self.run(withConfig: config, resolver: resolve, rejecter: reject)
        }
    }

    @objc func startFaceReAuth(_ config: NSDictionary,
                         resolver resolve: @escaping RCTPromiseResolveBlock,
                         rejecter reject: @escaping RCTPromiseRejectBlock) {
            DispatchQueue.main.async {
                self.run(withConfig: config, resolver: resolve, rejecter: reject)
            }
        }

    private func run(withConfig config: NSDictionary,
                     resolver resolve: @escaping RCTPromiseResolveBlock,
                     rejecter reject: @escaping RCTPromiseRejectBlock) {
        do {
            let authToken = GetSdkConfig.getAuthToken(config: config)
            let idenfySettingsV2 = IdenfyBuilderV2()
                .withAuthToken(authToken)
                .build()

            let idenfyController = IdenfyController.shared
            idenfyController.initializeIdenfySDKV2WithManual(idenfySettingsV2: idenfySettingsV2)

            let idenfyVC = idenfyController.instantiateNavigationController()

            idenfyVC.modalPresentationStyle = .fullScreen

            UIApplication.shared.windows.first?.rootViewController?.present(idenfyVC, animated: true)

            handleSdkCallbacks(idenfyController: idenfyController, resolver: resolve)

        } catch let error as NSError {
            reject("error", error.domain, error)
            return
        } catch {
            reject("error", "Unexpected error. Verify that config is structured correctly.", error)
            return
        }
    }

    private func handleSdkCallbacks(idenfyController: IdenfyController, resolver resolve: @escaping RCTPromiseResolveBlock) {
        idenfyController.handleIdenfyCallbacksWithManualResults(idenfyIdentificationResult: {
            idenfyIdentificationResult
                in
            let response = NativeResponseToReactNativeResponseMapper.map(o: idenfyIdentificationResult)
            resolve(response)
        })
    }

    private func runFaceReauth(withConfig config: NSDictionary,
                         resolver resolve: @escaping RCTPromiseResolveBlock,
                         rejecter reject: @escaping RCTPromiseRejectBlock) {
            do {
                let authToken = GetSdkConfig.getAuthToken(config: config)

                let idenfyController = IdenfyController.shared
                let faceReauthenticationInitialization = FaceAuthenticationInitialization(authenticationToken: authToken, withImmediateRedirect: false)
                idenfyController.initializeFaceAuthentication(faceAuthenticationInitialization: faceReauthenticationInitialization)

                let idenfyVC = idenfyController.instantiateNavigationController()

                idenfyVC.modalPresentationStyle = .fullScreen

                UIApplication.shared.windows.first?.rootViewController?.present(idenfyVC, animated: true)

                handleFaceReauthSdkCallbacks(idenfyController: idenfyController, resolver: resolve)

            } catch let error as NSError {
                reject("error", error.domain, error)
                return
            } catch {
                reject("error", "Unexpected error. Verify that config is structured correctly.", error)
                return
            }
        }

        private func handleFaceReauthSdkCallbacks(idenfyController: IdenfyController, resolver resolve: @escaping RCTPromiseResolveBlock) {
                idenfyController.handleIdenfyCallbacksForFaceAuthentication(faceReauthenticationResult: {
                    faceReauthenticationResult
                        in
                    let response = NativeResponseToReactNativeResponseMapper.mapFaceReauth(o: faceReauthenticationResult)
                    resolve(response)
                })
            }
}
