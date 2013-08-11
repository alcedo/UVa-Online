#! /bin/bash

if [ "$1" = "" ]; then
	echo "PLease specifiy input file !"
	exit
fi

#Execute the default Main Class as specified by UVA
java -classpath bin Main < $1
