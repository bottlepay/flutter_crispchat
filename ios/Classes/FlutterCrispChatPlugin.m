#import "FlutterCrispChatPlugin.h"
#if __has_include(<flutter_crispchat/flutter_crispchat-Swift.h>)
#import <flutter_crispchat/flutter_crispchat-Swift.h>
#else
// Support project import fallback if the generated compatibility header
// is not copied when this plugin is created as a library.
// https://forums.swift.org/t/swift-static-libraries-dont-copy-generated-objective-c-header/19816
#import "flutter_crispchat-Swift.h"
#endif

@implementation FlutterCrispChatPlugin
+ (void)registerWithRegistrar:(NSObject<FlutterPluginRegistrar>*)registrar {
  [SwiftFlutterCrispChatPlugin registerWithRegistrar:registrar];
}
@end
