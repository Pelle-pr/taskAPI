# Simple API for functional programming project

## http://localhost:8080/startcode-ca3/

### api/employees
- POST: {"name": "Bob Eriksen", "phone": 23943132, "email": "realbob@bobmail.bob"}
- GET: returns [{"name" : "abcd", ...}, {...}]
- GET (/{employeeId}): {"id" : {employeeId}, "name": "Bob Eriksen", "phone": 23943132, "email": "realbob@bobmail.bob"}
- DELETE (/{employeeId}): Returns deleted employee

### api/customers
- POST: {"name": "Hans Eriksen", "address": "Skøjtevej 81, 3700 Rønne", "phone": 9987832, "email": "hans@hansmail.com"}
- GET: returns [{"name" : "abcd", ...}, {...}]
- GET (/{customerId}): {"id" : {customerId}, "name": "Hans Eriksen", "address": "Skøjtevej 81, 3700 Rønne", "phone": 9987832, "email": "hans@hansmail.com"}
- DELETE (/{customerId}): Returns deleted customer

### api/tasks
- POST: 
{
    "title" : "Task #2",
    "date" : "22/04/2020",
    "description" : "Work work more work",
    "employeeList" : [
        {
            "id" : 2
        },
        {
            "id" : 1
        }
    ],
    "customer" : {
    "id" : 2
}
}

- GET (/{customerId]): 
[
    {
        "id": 1,
        "title": "Task #1",
        "date": "28/03/2020",
        "description": "Work work",
        "employeeList": [
            {
                "id": 1,
                "name": "Bob Hansen",
                "phone": 2394283,
                "email": "bob@bobmail.bob"
            }
        ],
        "customer": {
            "id": 2,
            "name": "Hans Eriksen",
            "address": "Skøjtevej 81, 3700 Rønne",
            "phone": 9987832,
            "email": "hans@hansmail.com"
        }
    },
    {
        "id": 2,
        "title": "Task #2",
        "date": "22/04/2020",
        "description": "Work work more work",
        "employeeList": [
            {
                "id": 2,
                "name": "Jønke Hansen",
                "phone": 2341223,
                "email": "gammelmand@rynkeby.dk"
            },
            {
                "id": 1,
                "name": "Bob Hansen",
                "phone": 2394283,
                "email": "bob@bobmail.bob"
            }
        ],
        "customer": {
            "id": 2,
            "name": "Hans Eriksen",
            "address": "Skøjtevej 81, 3700 Rønne",
            "phone": 9987832,
            "email": "hans@hansmail.com"
        }
    }
]
