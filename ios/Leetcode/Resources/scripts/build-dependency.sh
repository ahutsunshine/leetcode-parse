#!/bin/sh

# stop script on error
set -e

if [[ "$@" == *"--release"* ]]; then
  echo "Building release mode carthage dependency"
  carthage bootstrap --platform ios --cache-builds
else
  echo "Building debug mode carthage dependency"
  carthage bootstrap --platform ios --cache-builds --configuration Debug
fi
