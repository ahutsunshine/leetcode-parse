#!/bin/sh

#  run-swiftlint.sh
#  Telis
#
#  Created by Saul Mora on 7/10/17.
#  Copyright © 2017 Liulishuo. All rights reserved.

if which swiftlint >/dev/null; then
  swiftlint
else
  echo "warning: SwiftLint not installed, download from https://github.com/realm/SwiftLint"
fi
