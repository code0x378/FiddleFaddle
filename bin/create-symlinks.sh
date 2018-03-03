#!/usr/bin/env bash

cd ../

mkdir -p desktop-qt-widgets/resources
rm desktop-qt-widgets/resources/assets
ln -s "$PWD/graphics/global-assets/" "$PWD/desktop-qt-widgets/resources/assets"

mkdir -p desktop-qt-widgets-graphics/resources
rm desktop-qt-widgets-graphics/resources/assets
ln -s "$PWD/graphics/global-assets/" "$PWD/desktop-qt-widgets-graphics/resources/assets"

mkdir -p server-sparkjava/src/main/resources/public
rm server-sparkjava/src/main/resources/public/assets
ln -s "$PWD/graphics/global-assets/" "$PWD/server-sparkjava/src/main/resources/public/assets"

mkdir -p desktop-swing/src/main/resources
rm desktop-swing/src/main/resources/assets
ln -s "$PWD/graphics/global-assets/" "$PWD/desktop-swing/src/main/resources/assets"

mkdir -p server-adonis/public
rm server-adonis/public/assets
ln -s "$PWD/graphics/global-assets/" "$PWD/server-adonis/public/assets"