import { NativeModules } from 'react-native';

type IdenfyReactNativeType = {
  start(config: any): Promise<any>;
};

const { IdenfyReactNative } = NativeModules;

export default IdenfyReactNative as IdenfyReactNativeType;
