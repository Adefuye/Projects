#!/bin/bash
projectName=$1
mkdir $projectName
cd $projectName
touch Documentation.txt
echo "Created New Project: " $projectName
