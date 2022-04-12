#!/bin/bash

# recompile everything
./build-WolfPubDB.sh

# cd into bin directory
cd WolfPubDB/bin

# re-initialize the database
java InitDB

# start demo by running interface
java WolfCity

