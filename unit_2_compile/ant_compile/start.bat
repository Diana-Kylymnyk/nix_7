call ..\ant\setantenv.bat

call ant clean
call ant compile
call ant jar
call ant run