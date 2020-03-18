#/bin/bash

JAVAFX_PATH=~/.java/libraries/javafx-sdk-11.0.1/lib
MAIN_CLASS=SistemaSolar
PACKAGE_NAME=sistemasolar

SRC_DIRECTORY=src

# Move to the source code folder
cd $SRC_DIRECTORY

# Compile application
echo "Compiling application..."
javac --module-path $JAVAFX_PATH --add-modules=javafx.controls $PACKAGE_NAME/*.java

echo "Starting application..."
# Run application
java --module-path $JAVAFX_PATH --add-modules=javafx.controls $PACKAGE_NAME.$MAIN_CLASS

cd ..
