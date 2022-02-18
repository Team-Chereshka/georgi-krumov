del /s /q *.class
cd src
javac -cp . org/sanity/consoleForum/*.java
java org/sanity/consoleForum/Launcher
del /s /q *.class
PAUSE