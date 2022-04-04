#!/bin/bash

################################################################################
######################### Compile WolfPubDBJava Files ##########################
################################################################################
# Compiles all java files in "./WolfPubDB/src" and places in target directory "./WolfPubDB/bin"

# set path variable
source_path="./WolfPubDB/src/*.java"
if [[ "$OSTYPE" == "msys" ]]; then
  class_path="."
else
  class_path="."
fi
bin_path="./WolfPubDB/bin"

# remove existing files
rm -rf $bin_path

# compile files
javac $source_path -cp $class_path -d $bin_path  

# print message and class path when finished
echo
echo "WolfPubDB Java File Compilation Complete"
echo "Bin Path: ${bin_path}"