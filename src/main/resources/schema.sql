CREATE table IF NOT EXISTS student(rollNo int AUTO_INCREMENT primary key,
studentName varchar(50)Not Null,
city varchar(30),
mobileNumber varchar(12) not null,
createdAt TIMESTAMP DEFAULT NULL ,
updatedAt TIMESTAMP DEFAULT NULL
);

