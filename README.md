# Huszárcsere

Szoftverfejlesztés kurzusra készült beadandó projekt, ami a huszárcsere játékot valósítja meg. 

## Leírás
* Adott egy 3 x 3 tábla. A legfelső sorban három fekete, a legalsó sorban három fehér huszár van.
* A cél az, hogy fent legyenek a fekete, és lent a fehér huszárok. 
* A huszár ló lépésben tud csak lépni, azaz vagy vízszintesen tesz meg 2 lépést és függőlegesen 1-et, vagy függőlegesen 2, és vízszintesen 1 lépést. 
* Felváltva kell lépni a fehér és a fekete huszárokkal. 
* A játék egyelőre tesztüzemmódban, konzolosan működik. A használatához meg kell adni az adott huszár koordinátáit. Először a sort, majd az oszlopot. Ezután pedig a választott helyet, ahova szeretné rakni a huszárt. 

## Project futtatása
```
git clone https://github.com/Dina-miss/huszarcsere-project.git
cd huszarcsere-project/
mvn package
java -jar ./target/huszarcsere-project-1.0-jar-with-dependencies.jar
```