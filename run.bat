set path=C:\Program Files\JavaJDK\bin
set include=C:\Program Files\JavaJDK\include
set lib=C:\Program Files\JavaJDK\lib
set link=C:\Program Files\JavaJDK\bin
javap -c Main>1.txt
javac -version Main.java
java Main
javadoc Main.java -d 1
pause