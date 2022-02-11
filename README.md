# JC01-APP

> Contains a detailed version of making a generic code for database operation

1. `StudentDaoOldImpl` is the first version
2. `StudentDaoSimpleImpl` is the second version
3. `StudentDaoJdbcTemplateImpl` is the third version

> Look for the difference in the code base how the code is developed up to the third base.

## What does this application contain

1. Commandline based CRUD Operation based application with MySQL as a DB [link](https://github.com/mnzit/JC01-APP/tree/ae1220a70627d8f78997c552887d773961efc69d)


```
./run.sh
```
Kill Port Mac & Linux
```
lsof -nP -iTCP -sTCP:LISTEN | grep 8080                                
kill -9 84847   
```

Kill Port Windows
```
netstat -ano | findstr :8080
taskkill /PID 84847 /F
```

> major changes during moving from payara micro to web-app-runner (tomcat)

```xml
        <dependency>
            <groupId>org.hibernate</groupId>
            <artifactId>hibernate-validator</artifactId>
            <version>5.2.4.Final</version>
        </dependency>
        <dependency>
            <groupId>jakarta.xml.bind</groupId>
            <artifactId>jakarta.xml.bind-api</artifactId>
            <version>2.3.2</version>
        </dependency>
        <dependency>
            <groupId>org.glassfish.jaxb</groupId>
            <artifactId>jaxb-runtime</artifactId>
            <version>2.3.2</version>
        </dependency>
```

```java
package com.ggic.app.request;


import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class StudentSaveRequest {

    @NotEmpty
    private String name;
    @NotNull
    private Date dob;
    @NotEmpty
    private String address;
    @NotEmpty
    private String contactNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }
}
```
