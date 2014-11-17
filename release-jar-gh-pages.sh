#!/bin/bash

export PS4="`tput setaf 3`>>> `tput sgr0`"      # output lines as executed in colored intended style
set -x                                          # verbose output (print each line as it is executed)
set -e                                          # stop at first error

mvn clean install
mkdir -p temp
cd temp

# clone oneapi-java repo
git clone -b gh-pages https://github.com/infobip/oneapi-java.git
cd oneapi-java

# replace .jar file
rm -f *.jar
cp ../../target/oneapi-java-*-SNAPSHOT.jar ./

# get new .jar file name
newjar=`ls *.jar`
echo $newjar

# deleting old gh-pages branch
cd ..
mv oneapi-java oneapi-java-new-gh-pages
git clone https://github.com/infobip/oneapi-java.git
cd oneapi-java
git push origin --delete gh-pages

# checkout a new orphan gh-pages branch and copy transition files to it
git checkout -f --orphan gh-pages
rm -rf *
cp -R ../oneapi-java-new-gh-pages/* ./

# replace link in file
# hardcoded line 26 in index.html!!!!!
replacement="<li><a href=\"https:\/\/github.com\/infobip\/oneapi-java\/blob\/gh-pages\/$newjar?raw=true\">Download <strong>JAR File<\/strong><\/a><\/li>"
sed -i "26s/.*/$replacement/" index.html

# git commit & push changes
git add --all
git commit -m "Initial commit. Version $newjar."
git push origin gh-pages

# cleanup
cd ../..
rm -rf temp
