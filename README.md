# Library Management System
To manage book addition to library , borrow and return from library
swagger url for the application http://localhost:8080/swagger-ui/index.html#/
Database - MySQL ,name-librarydb
API Endpoints are given below:
● Implement RESTful endpoints to handle the following operations:
● Book management endpoints:
● GET /bookapi/books: Retrieve a list of all books.
● GET /bookapi/books/{id}: Retrieve details of a specific book by ID.
● POST /bookapi/books: Add a new book to the library.
● PUT /bookapi/books/{id}: Update an existing book's information.
● DELETE /bookapi/books/{id}: Remove a book from the library.
● Patron management endpoints:
● GET /patronapi/patrons: Retrieve a list of all patrons.
● GET /patronapi/patrons/{id}: Retrieve details of a specific patron by ID.
● POST /patronapi/patrons: Add a new patron to the system.
● PUT /patronapi/patrons/{id}: Update an existing patron's information.
● DELETE /patronapi/patrons/{id}: Remove a patron from the system.
● Borrowing endpoints:
● POST /borrowapi/borrow/{bookId}/patron/{patronId}: Allow a patron to borrow a book.
● PUT /borrowapi/return/{bookId}/patron/{patronId}: Record the return of a borrowed book by a patron.
