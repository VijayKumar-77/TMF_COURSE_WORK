create database 
PaymentsApp;

show databases;

use 
PaymentsApp;

create table User(
UserID      int not null,
FirstName   varchar(10),
LastName    varchar(10),
PhNo        long,
DOB         date,
Address     varchar(50),
Password    varchar(10),
primary key (UserID)
);

desc User;

create table Wallet(
UserID          int not null,
CurrentBalance  double,
primary key     (UserID),
foreign key     (UserID) 
references       User(UserID)
);

desc Wallet;

create table BankAccount(
UserID          int not null,
AccountNumber   int not null,
BankName        varchar(20),
IFSCCODE        varchar(10),
AccountType     varchar(10),
BankAccountPin  varchar(10),
primary key     (AccountNumber),
foreign key     (UserID)
references       User (UserID));

desc BankAccount;

create table Transaction(
UserID                 int not null,
TransactionID          int not null,
TransactionSource      varchar(20),
TransactionDestination varchar(20),
TransactionAmount      double,
primary key            (TransactionID),
foreign key            (UserID)
references             User (UserID)
);

desc Transaction;

