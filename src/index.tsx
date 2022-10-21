import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'idenfy-react-native' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo Go\n';

const IdenfyReactNative = NativeModules.IdenfyReactNative
  ? NativeModules.IdenfyReactNative
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export function start(config: any): Promise<any> {
  return IdenfyReactNative.start(config);
}

export function startFaceReAuth(config: any): Promise<any> {
  return IdenfyReactNative.startFaceReAuth(config);
}


