#!/bin/bash

################################################################################
######################### Compile WolfPubDBJava Files ##########################
################################################################################
# Compiles all java files in "./WolfPubDB/src" and places in target directory "./WolfPubDB/bin"

# set path variable
source_dir="./WolfPubDB/src/"
source_files="${source_dir}*/*.java"
if [[ "$OSTYPE" == "msys" ]]; then
  class_path="."
else
  class_path="."
fi
bin_dir="./WolfPubDB/bin/"

# remove existing files
rm -rf $bin_dir

# compile files
mkdir -p $bin_dir
javac $source_files -cp $class_path -d $bin_dir

# print message and class path when finished
echo
echo "WolfPubDB Java File Compilation Complete"
echo "========================================"
echo "Source Dir.: ${source_dir}"
echo "Bin Dir.:    ${bin_dir}"
echo