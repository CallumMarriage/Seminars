**Seminars**

To run tests run ```mvn clean install```

Model contains java class.

Use Application.java main method to run application

Files used are contained in the resources directory in main.

*File:* ./src/main/resources/seminars.txt 

**seminar.txt format**

"Tab separated sections"

**0** : Title,
**1** : Content,
**2** : Number Of Lecturers 
**3** : First Lecturer Name,
**4** : First Lecturer Type,

*For additional lecturers*
**5** : Additional Lecturer Name,
**6** : Additional Lecturer Type,
....

**NOTE**

Lecturers in the same seminar can not have the same name; they can have the same type but not name.
