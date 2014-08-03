create table Account (id varchar unique,
						username varchar unique,
						password varchar not null,
						firstName varchar not null, 
						lastName varchar not null,
						primary key (id));
