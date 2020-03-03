#!/bin/bash
mkdir secretfiles #creating new directory
cp grep.tar secretfiles # copying the grep tar file into secretfiles
cd secretfiles #changing dir into secretfiles
tar -xvf grep.tar #extracting the tar file
touch authcode.txt #creating a text file to store the output
grep "SECRET AUTH CODE" *== >authcode.txt #finding which file has the words and outputting it to a txt file
rm *== | rm *.tar #removing all files except authcode.txt
echo "Found the secret auth code! Saved to authcode.txt" #printing confirmation on to screen
