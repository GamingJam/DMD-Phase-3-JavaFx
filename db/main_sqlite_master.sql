INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('table', 'user', 'user', 2, 'CREATE TABLE user
(
	id int
		constraint user_pk
			primary key
, name varchar(32), password varchar(32))');
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'sqlite_autoindex_user_1', 'user', 3, null);
INSERT INTO sqlite_master (type, name, tbl_name, rootpage, sql) VALUES ('index', 'user_name_uindex', 'user', 4, 'CREATE UNIQUE INDEX user_name_uindex
	on user (name)');