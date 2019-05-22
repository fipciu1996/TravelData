CREATE TABLE IF NOT EXISTS Travel(
 travel_id serial PRIMARY KEY,
 locale VARCHAR (5) UNIQUE NOT NULL,
 destination VARCHAR (50) NOT NULL,
 departure_time TIMESTAMP NOT NULL,
 return_time TIMESTAMP,
 price INT NOT NULL,
 currency VARCHAR(5)
);