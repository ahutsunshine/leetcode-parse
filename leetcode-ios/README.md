# LeetCode

## Build Environment

1. Xcode 10.1 + [Carthage](https://github.com/Carthage/Carthage).

`npm` and `Carthage` are dependency managment tools for javascript and iOS respectively. Check their project website for installation guide.

## Build

After checking out project, build dependencies from carthage.

```shell
$ Resources/scripts/build-dependency.sh --carthage
```
`--carthage`：for building carthage dependency

PS: Script will  build carthage in debug mode. If you prefer release mode, add `--release` in addition.


## Dependencies

### Client Infra managed dependencies

- [Quicksilver](https://git.llsapp.com/client-infra/Quicksilver). Our Network library, handling HTTPDNS & Certificate Verification and so on.
- [Rainbow](https://git.llsapp.com/client-infra/Rainbow). Our project module management framework & protocol.

### Third Party Dependencies

- [PureLayout](https://github.com/PureLayout/PureLayout). An autoLayout helper. You can still use iOS layout anchor or other machanism if you wish.
- [SVProgressHUD](https://github.com/SVProgressHUD/SVProgressHUD). Our Heads up display (HUD).
- [R.swift](https://github.com/mac-cain13/R.swift). Get strong typed, autocompleted resources like images, fonts and segues in Swift projects.
- [FLEX](https://github.com/Flipboard/FLEX). A set of in-app debugging and exploration tools for iOS development. Only copy when DEBUG or QA_BUILD
- [RxSwift](https://https://github.com/ReactiveX/RxSwift). Rx is a generic abstraction of computation expressed through Observable<Element> interface.
- [CocoaLumberjack](https://github.com/CocoaLumberjack/CocoaLumberjack)CocoaLumberjack is a fast & simple, yet powerful & flexible logging framework for Mac and iOS.
- [KeychainSwift](https://github.com/evgenyneu/keychain-swift). Helper functions for storing text in Keychain.

## Project Architecture

Read our module management protocol [Rainbow](https://git.llsapp.com/client-infra/Rainbow) carefully before trying to have a comprehension over this project.

### Application, Modules and Frameworks
Here are some import files / directories you need to known.

#### LeetCode
Main application
- `Rainbow` Project module management configuration

#### LeetCodeService
- `Configuration` Every constant info like server base url, app key and so on should be managed in `Configuration` not `Service`
- `UserService` User model & Login/Logout.


#### LeetCodeUI
UI dependency (Layout and etc.)
- `Style` LeetCode UI styleguide. Look into this when you need named Font / Color
