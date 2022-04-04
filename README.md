# CSC540-Project

## Compile Database

To compile all the java files for our WolfPubDB implementation, simply run the
included "build-WolfPubDB.sh" shell script from the root directory, as shown
in the command below.

```bash
./build-WolfPubDB.sh
```

This will compile all java files included in the "WolfPubDB/src/" directory and
place the binaries into the "WolfPubDB/bin/" directory.


## Populate Database

After compiling all the java source code, as described above, the WolfPubDB can
be initialized with test data by running the "initDB" executable from the bin
directory. Below is an example of this command:

```bash
cd ./WolfPubDB/bin ; java ./WolfPubDB/bin/initDB
```

Note that this command will remove any pre-existing WolfPubDB database and all
of its tables/records.