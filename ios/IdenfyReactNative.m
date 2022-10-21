#import <React/RCTBridgeModule.h>

@interface RCT_EXTERN_MODULE(IdenfyReactNative, NSObject)

RCT_EXTERN_METHOD(
    start:(NSDictionary *)config
    withResolver:(RCTPromiseResolveBlock)resolve
    withRejecter:(RCTPromiseRejectBlock)reject
)

RCT_EXTERN_METHOD(
    startFaceReAuth:(NSDictionary *)config
    withResolver:(RCTPromiseResolveBlock)resolve
    withRejecter:(RCTPromiseRejectBlock)reject
)

+ (BOOL)requiresMainQueueSetup
{
  return NO;
}

@end
