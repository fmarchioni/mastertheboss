CREATE TABLE account(
   user_id VARCHAR PRIMARY KEY,
   username VARCHAR (50) UNIQUE NOT NULL,
   password VARCHAR (50) NOT NULL,
   country VARCHAR (355) NOT NULL
);
