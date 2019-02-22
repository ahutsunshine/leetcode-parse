# LeetCode

## Build Environment

1. [Cocos Creator](http://www.cocos2d-x.org/creator) v1.9.3 + [npm](https://www.npmjs.com). Cocos Creator must be installed in Application folder. 
2. Xcode 10.1 + [Carthage](https://github.com/Carthage/Carthage).

npm and Carthage are dependency managment tools for javascript and iOS respectively. Check their project website for installation guide.

## Build

After check out project, build dependencies from carthage:

```shell
$ Resources/scripts/build-dependency.sh --carthage
```

This commend will build several dependency:

1. Carthage. For game developer, only need to call once unless your Cocos Creator / Xcode is upgraded. If you need build carthage dependency, add `--carthage` as script argument. Note that `--carthage` mode will also overwrite current game bundle.


PS: Script will build game and carthage in debug mode. If you prefer release mode, add `--release` in addition.


## Dependencies

### Client Infra managed dependencies

- [Quicksilver](https://git.llsapp.com/client-infra/Quicksilver). Our Network library, handling HTTPDNS & Certificate Verification and so on.

### Third Party Dependencies 

- [PureLayout](https://github.com/PureLayout/PureLayout). An autoLayout helper. You can still use iOS layout anchor or other machanism if you wish.
- [SVProgressHUD](https://github.com/SVProgressHUD/SVProgressHUD). Our Heads up display (HUD).
- [R.swift](https://github.com/mac-cain13/R.swift). Get strong typed, autocompleted resources like images, fonts and segues in Swift projects.
- [FLEX](https://github.com/Flipboard/FLEX). A set of in-app debugging and exploration tools for iOS development. Only copy when DEBUG or QA_BUILD
- [RxSwift](https://https://github.com/ReactiveX/RxSwift). Rx is a generic abstraction of computation expressed through Observable<Element> interface.
- [CocoaLumberjack](https://github.com/CocoaLumberjack/CocoaLumberjack)CocoaLumberjack is a fast & simple, yet powerful & flexible logging framework for Mac and iOS.
