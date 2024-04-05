# Library Management System
To manage book addition to library , borrow and return from library<br />
swagger url for the application http://localhost:8080/swagger-ui/index.html#/<br />
Database - MySQL ,name-librarydb<br />
# API Endpoints are given below: <br />
● Implement RESTful endpoints to handle the following operations:<br />
● Book management endpoints:<br />
● GET /bookapi/books: Retrieve a list of all books.<br />
● GET /bookapi/books/{id}: Retrieve details of a specific book by ID.<br />
● POST /bookapi/books: Add a new book to the library.<br />
● PUT /bookapi/books/{id}: Update an existing book's information.<br />
● DELETE /bookapi/books/{id}: Remove a book from the library.<br />
● Patron management endpoints:<br />
● GET /patronapi/patrons: Retrieve a list of all patrons.
● GET /patronapi/patrons/{id}: Retrieve details of a specific patron by ID.
● POST /patronapi/patrons: Add a new patron to the system.
● PUT /patronapi/patrons/{id}: Update an existing patron's information.
● DELETE /patronapi/patrons/{id}: Remove a patron from the system.
● Borrowing endpoints:
● POST /borrowapi/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
● PUT /borrowapi/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
