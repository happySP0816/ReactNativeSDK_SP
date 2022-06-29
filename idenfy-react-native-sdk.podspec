require "json"

package = JSON.parse(File.read(File.join(__dir__, "package.json")))

Pod::Spec.new do |s|
  s.name         = "idenfy-react-native-sdk"
  s.version      = package["version"]
  s.summary      = package["description"]
  s.homepage     = package["homepage"]
  s.license      = package["license"]
  s.authors      = package["author"]

  s.platforms    = { :ios => "9.0" }
  s.source       = { :git => "https://github.com/idenfy/ReactNativeSDK.git", :tag => "#{s.version}" }


  s.source_files = "ios/**/*.{h,c,m,swift}"
  s.requires_arc = true


  s.dependency "React"
  s.dependency "iDenfySDK/iDenfyLiveness", "7.4.0"
end
