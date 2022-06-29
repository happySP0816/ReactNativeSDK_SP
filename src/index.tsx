import { NativeModules } from 'react-native';

type IdenfyReactNativeType = {
  startFaceReAuth(config: any): Promise<any>;
  start(config: any): Promise<any>;
};

const { IdenfyReactNative } = NativeModules;

export default IdenfyReactNative as IdenfyReactNativeType;
