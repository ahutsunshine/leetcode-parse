#!/bin/sh

#  update-build-version-string.sh
#  Telis
#
#  Created by Saul Mora on 7/10/17.
#  Copyright © 2017 Liulishuo. All rights reserved.

GIT=/usr/bin/git
PLIST=/usr/libexec/PlistBuddy

PRODUCT_PLIST_FILE_PATH="${BUILT_PRODUCTS_DIR}/${FULL_PRODUCT_NAME}/Info.plist"
versionNumber=$($PLIST -c "Print :CFBundleShortVersionString" "${INFOPLIST_FILE}")

if [[ $OTHER_SWIFT_FLAGS == *-DQA_BUILD* ]]
then
buildNumber="`$GIT rev-list HEAD --abbrev-commit --max-count=1`"
elif [[ $OTHER_SWIFT_FLAGS == *-DINTERNAL_ENTERPRISE* ]]
then
buildNumber="`$GIT rev-list HEAD --count`-Internal"
else
buildNumber="`$GIT rev-list HEAD --count`"
fi

$PLIST -c "Set :CFBundleVersion $buildNumber" "${PRODUCT_PLIST_FILE_PATH}"
$PLIST -c "Set :CFBundleShortVersionString $versionNumber" "${PRODUCT_PLIST_FILE_PATH}"

