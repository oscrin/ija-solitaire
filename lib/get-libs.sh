#!/bin/sh

wget "www.stud.fit.vutbr.cz/~xkubis15/res.zip" -O "./lib/res.zip"
unzip -o "./lib/res.zip" -d "./lib"
rm "./lib/res.zip"
