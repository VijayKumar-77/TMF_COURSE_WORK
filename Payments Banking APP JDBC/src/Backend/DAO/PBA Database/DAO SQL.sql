create database Payments_Banking_App;

show databases;

use Payments_Banking_App;

-- drop database Payments_Banking_App;

create table User_Details(
User_ID          int not null auto_increment,
First_Name       varchar(10),
Last_Name        varchar(10),
Phone_Num        varchar(15),
DOB              date,
Address          varchar(50),
Wallet_Balance   double,
Password         varchar(10),
primary key      (User_ID)
);

desc User_Details;

-- drop table User_Details;

create table Wallet_Details(
User_ID          int not null,
Current_Balance  double default 0.00,
primary key      (User_ID),
foreign key      (User_ID) 
references       User_Details (User_ID)
);

 desc Wallet_Details;
 
 -- drop table Wallet_Details
 

create table Bank_Account_Types(
Bank_ACCT_Type        enum ("CURRENT", "LOAN", "SAVINGS", "SALARY"),
Bank_ACCT_Type_ID     int not null,
ACCT_Type_Code        varchar(3),
ACCT_Type_Description varchar(25),
primary key           (Bank_ACCT_Type_ID)
);

desc Bank_Account_Types;

-- drop table Account_Types;


create table Bank_Acct_Details(
User_ID              int not null,
Bank_ACCT_Type_ID    int not null,
Bank_Account_Num     int not null,
Bank_Account_Name    varchar(20),
IFSC_CODE            varchar(10),
Bank_ACCT_Pin        varchar(10),
primary key          (Bank_Account_Num),
foreign key          (User_ID)
references           User_Details (User_ID),
foreign key          (Bank_ACCT_Type_ID)
references           Bank_Account_Types (Bank_ACCT_Type_ID)
);

desc Bank_Acct_Details;

-- drop table Bank_Acct_Details;



create table Transaction_Details(
Trnxn_ID          int not null,
Trnxn_Amount      double,
Trnxn_Date        datetime,
Trnxn_Type        enum ("CREDIT", "DEBIT"),
Trnxn_User_ID     int not null,
Trnxn_ACCT_Type   enum ("CASH", "BANK_ACCOUNT", "WALLET"),
primary key       (Trnxn_ID),
foreign key       (Trnxn_User_ID) 
references        User_Details (User_ID)
);

desc Transaction_Details;

-- drop table Transaction_Detailsl